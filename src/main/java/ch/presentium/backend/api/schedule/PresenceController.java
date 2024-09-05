package ch.presentium.backend.api.schedule;

import ch.presentium.backend.annotation.security.IsStudentUser;
import ch.presentium.backend.annotation.security.IsTeacherUser;
import ch.presentium.backend.api.common.DateRange;
import ch.presentium.backend.api.exception.ObjectNotFoundException;
import ch.presentium.backend.api.schedule.model.PresenceViewModel;
import ch.presentium.backend.business.model.user.Student;
import ch.presentium.backend.business.model.user.User;
import ch.presentium.backend.business.repository.PresenceRepository;
import ch.presentium.backend.business.repository.UserRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/presences")
@Tag(name = "School class presence management", description = "Manage school classes and their sessions")
@RequiredArgsConstructor
public class PresenceController {

    private final PresenceRepository presenceRepository;
    private final UserRepository userRepository;

    @GetMapping
    @IsTeacherUser
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public List<PresenceViewModel> getPresences(
        @RequestParam(required = false) UUID studentId,
        @RequestParam(required = false) Long schoolClassId,
        DateRange dateRange
    ) {
        return presenceRepository.calculateAttendance(schoolClassId, dateRange, studentId);
    }

    @GetMapping(params = "studentId=@me")
    @IsStudentUser
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public List<PresenceViewModel> getPresences(
        @RequestParam(required = false) Long schoolClassId,
        @AuthenticationPrincipal Jwt jwt,
        DateRange dateRange
    ) {
        var student = userRepository
            .findById(jwt.getSubject())
            .map(User::getPerson)
            .filter(Student.class::isInstance)
            .map(Student.class::cast)
            .orElseThrow(() -> ObjectNotFoundException.forStudent(jwt.getSubject()));
        return presenceRepository.calculateAttendance(schoolClassId, dateRange, student.getId());
    }
}
