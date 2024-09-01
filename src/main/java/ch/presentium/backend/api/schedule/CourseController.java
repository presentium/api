package ch.presentium.backend.api.schedule;

import ch.presentium.backend.annotation.security.IsTeacherUser;
import ch.presentium.backend.api.exception.ObjectNotFoundException;
import ch.presentium.backend.business.model.user.Teacher;
import ch.presentium.backend.business.model.user.User;
import ch.presentium.backend.business.repository.UserRepository;
import ch.presentium.backend.business.service.ScheduleService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarParserImpl;
import net.fortuna.ical4j.data.ParserException;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/courses", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "School courses management", description = "Manage courses and their classes")
@RequiredArgsConstructor
public class CourseController {

    private final ScheduleService scheduleService;
    private final UserRepository userRepository;

    @PostMapping(path = "/actions/import", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @IsTeacherUser
    @Transactional(rollbackFor = Throwable.class)
    public void importCourses(@AuthenticationPrincipal Jwt jwt, @RequestPart("calendar") Resource calendarFile)
        throws IOException, ParserException {
        var teacher = userRepository
            .findById(jwt.getSubject())
            .map(User::getPerson)
            .filter(Teacher.class::isInstance)
            .map(Teacher.class::cast)
            .orElseThrow(() -> ObjectNotFoundException.forTeacher(UUID.fromString(jwt.getSubject())));

        var calendar = new CalendarBuilder(new CalendarParserImpl(true)).build(calendarFile.getInputStream());
        scheduleService.importCourses(teacher, calendar);
    }
}
