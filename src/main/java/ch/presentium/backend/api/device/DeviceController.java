package ch.presentium.backend.api.device;

import ch.presentium.backend.annotation.security.IsAdminUser;
import ch.presentium.backend.annotation.security.IsTeacherUser;
import ch.presentium.backend.api.device.mapper.DeviceMapper;
import ch.presentium.backend.api.device.model.DeviceViewModel;
import ch.presentium.backend.api.device.request.AssignmentRequestBody;
import ch.presentium.backend.api.device.request.EnrollStudentRequestBody;
import ch.presentium.backend.api.device.request.PresenceCheckRequestBody;
import ch.presentium.backend.api.exception.ObjectNotFoundException;
import ch.presentium.backend.api.mapping.MappingContext;
import ch.presentium.backend.business.model.user.Teacher;
import ch.presentium.backend.business.model.user.User;
import ch.presentium.backend.business.repository.PresenceBoxRepository;
import ch.presentium.backend.business.repository.SchoolClassRepository;
import ch.presentium.backend.business.repository.StudentRepository;
import ch.presentium.backend.business.repository.TeacherRepository;
import ch.presentium.backend.business.repository.UserRepository;
import ch.presentium.backend.business.service.ClassSessionService;
import ch.presentium.backend.rpc.DeviceBridgeService;
import ch.presentium.backend.rpc.EnrollStudentEvent;
import ch.presentium.backend.rpc.PresenceCheckEvent;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/devices", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Devices Management", description = "Manage reader devices connected to the system")
@RequiredArgsConstructor
public class DeviceController {

    private final PresenceBoxRepository deviceRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final DeviceBridgeService deviceBridgeService;
    private final ClassSessionService classSessionService;
    private final DeviceMapper deviceMapper;
    private final MappingContext mappingContext;

    @GetMapping(params = "assigned=@me")
    @Operation(
        summary = "Get devices",
        parameters = {
            @Parameter(
                name = "assigned",
                description = "Return only assigned devices",
                schema = @Schema(allowableValues = { "@me" })
            ),
        }
    )
    @IsTeacherUser
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public List<DeviceViewModel> getAssignedDevices(@AuthenticationPrincipal Jwt jwt) {
        return userRepository
            .findById(jwt.getSubject())
            .map(User::getPerson)
            .filter(Teacher.class::isInstance)
            .map(Teacher.class::cast)
            .map(Teacher::getDevices)
            .stream()
            .flatMap(Set::stream)
            .map(device -> deviceMapper.map(device, mappingContext))
            .toList();
    }

    @GetMapping
    @IsAdminUser
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public List<DeviceViewModel> getDevices() {
        return deviceRepository.findAll().stream().map(device -> deviceMapper.map(device, mappingContext)).toList();
    }

    @GetMapping("/{deviceId}")
    @IsTeacherUser
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public DeviceViewModel getDevice(@PathVariable UUID deviceId) {
        return deviceRepository
            .findById(deviceId)
            .map(device -> deviceMapper.map(device, mappingContext))
            .orElseThrow(() -> ObjectNotFoundException.forDevice(deviceId));
    }

    @PostMapping("/{deviceId}/actions/assign")
    @IsAdminUser
    @Transactional(rollbackFor = Throwable.class)
    public void assignDevice(@PathVariable UUID deviceId, @Valid @RequestBody AssignmentRequestBody requestBody) {
        var device = deviceRepository.findById(deviceId).orElseThrow(() -> ObjectNotFoundException.forDevice(deviceId));
        var teacher = teacherRepository
            .findById(requestBody.teacher())
            .orElseThrow(() -> ObjectNotFoundException.forTeacher(requestBody.teacher()));
        device.setTeacher(teacher);
    }

    @PostMapping("/{deviceId}/actions/unassign")
    @IsAdminUser
    @Transactional(rollbackFor = Throwable.class)
    public void unassignDevice(@PathVariable UUID deviceId) {
        var device = deviceRepository.findById(deviceId).orElseThrow(() -> ObjectNotFoundException.forDevice(deviceId));
        device.removeTeacher();
    }

    @PostMapping("/{deviceId}/actions/enroll-student")
    @IsTeacherUser
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void enrollStudent(@PathVariable UUID deviceId, @Valid @RequestBody EnrollStudentRequestBody requestBody) {
        var device = deviceRepository.findById(deviceId).orElseThrow(() -> ObjectNotFoundException.forDevice(deviceId));
        var student = studentRepository
            .findById(requestBody.student())
            .orElseThrow(() -> ObjectNotFoundException.forStudent(requestBody.student()));

        deviceBridgeService.send(
            device.getName(),
            EnrollStudentEvent.newBuilder().setStudentId(student.getId().toString()).build()
        );
    }

    @PostMapping("/{deviceId}/actions/presence-check")
    @IsTeacherUser
    @Transactional(rollbackFor = Throwable.class)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public void presenceCheck(@PathVariable UUID deviceId, @Valid @RequestBody PresenceCheckRequestBody requestBody) {
        var device = deviceRepository.findById(deviceId).orElseThrow(() -> ObjectNotFoundException.forDevice(deviceId));
        var schoolClass = schoolClassRepository
            .findById(requestBody.schoolClass())
            .orElseThrow(() -> ObjectNotFoundException.forSchoolClass(requestBody.schoolClass()));

        var session = classSessionService.createSession(schoolClass);

        deviceBridgeService.send(
            device.getName(),
            PresenceCheckEvent.newBuilder().setSessionId(session.getId().toString()).build()
        );
    }
}
