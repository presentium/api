package ch.presentium.backend.api.schedule;

import ch.presentium.backend.annotation.security.IsTeacherUser;
import ch.presentium.backend.api.schedule.mapper.SchoolClassMapper;
import ch.presentium.backend.api.schedule.model.SchoolClassViewModel;
import ch.presentium.backend.business.model.user.Teacher;
import ch.presentium.backend.business.model.user.User;
import ch.presentium.backend.business.repository.UserRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/school-classes", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "School class session management", description = "Manage school classes and their sessions")
@RequiredArgsConstructor
public class SchoolClassController {

    private final UserRepository userRepository;
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
}
