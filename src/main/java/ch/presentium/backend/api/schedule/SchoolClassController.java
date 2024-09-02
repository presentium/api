package ch.presentium.backend.api.schedule;

import ch.presentium.backend.annotation.security.IsTeacherUser;
import ch.presentium.backend.api.exception.ObjectNotFoundException;
import ch.presentium.backend.api.schedule.mapper.SchoolClassMapper;
import ch.presentium.backend.api.schedule.model.SchoolClassViewModel;
import ch.presentium.backend.business.model.user.Student;
import ch.presentium.backend.business.model.user.Teacher;
import ch.presentium.backend.business.model.user.User;
import ch.presentium.backend.business.repository.SchoolClassRepository;
import ch.presentium.backend.business.repository.StudentRepository;
import ch.presentium.backend.business.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/school-classes", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "School class session management", description = "Manage school classes and their sessions")
@RequiredArgsConstructor
public class SchoolClassController {

    private final UserRepository userRepository;
    private final SchoolClassRepository schoolClassRepository;
    private final StudentRepository studentRepository;
    private final SchoolClassMapper schoolClassMapper;

    @GetMapping(params = "teacher=@me")
    @Operation(
        summary = "Get school classes",
        parameters = {
            @Parameter(
                name = "teacher",
                description = "Return only my classes",
                schema = @Schema(allowableValues = { "@me" })
            ),
        }
    )
    @IsTeacherUser
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public List<SchoolClassViewModel> getSelfClasses(
        @AuthenticationPrincipal Jwt jwt,
        @RequestParam(name = "today", defaultValue = "false") boolean today
    ) {
        return userRepository
            .findById(jwt.getSubject())
            .map(User::getPerson)
            .filter(Teacher.class::isInstance)
            .map(Teacher.class::cast)
            .map(Teacher::getClasses)
            .stream()
            .flatMap(Set::stream)
            .filter(schoolClass -> !today || schoolClass.getDayOfWeek() == DayOfWeek.from(LocalDate.now()))
            .map(schoolClassMapper::map)
            .sorted(Comparator.comparing(SchoolClassViewModel::getName))
            .toList();
    }

    @GetMapping
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public List<SchoolClassViewModel> getAllClasses() {
        return schoolClassRepository.findAll().stream().map(schoolClassMapper::map).toList();
    }

    @GetMapping(params = "filter")
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public List<SchoolClassViewModel> getMyClasses(@RequestParam(name = "filter") String name) {
        return schoolClassRepository.findByStudentOrTeacherName(name).stream().map(schoolClassMapper::map).toList();
    }

    @GetMapping(path = "/{schoolClassId}")
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public SchoolClassViewModel getClass(@PathVariable Long schoolClassId) {
        return schoolClassRepository
            .findById(schoolClassId)
            .map(schoolClassMapper::map)
            .orElseThrow(() -> ObjectNotFoundException.forSchoolClass(schoolClassId));
    }

    @PostMapping(path = "/{schoolClassId}/actions/import-students")
    @IsTeacherUser
    @Transactional(rollbackFor = Throwable.class)
    public void importStudents(
        @PathVariable Long schoolClassId,
        @RequestBody @Valid @NotEmpty List<@NotBlank String> studentNames
    ) {
        var schoolClass = schoolClassRepository
            .findById(schoolClassId)
            .orElseThrow(() -> ObjectNotFoundException.forSchoolClass(schoolClassId));

        studentNames.forEach(name -> {
            var student = studentRepository
                .findByFullName(name)
                .orElseGet(() -> (Student) new Student().setFullName(name));

            schoolClass.addStudent(student);
        });
    }
}
