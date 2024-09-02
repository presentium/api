package ch.presentium.backend.api.schedule;

import ch.presentium.backend.business.model.schedule.ClassSession;
import ch.presentium.backend.business.service.ClassSessionService;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/sessions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Class session management", description = "Manage class sessions")
@RequiredArgsConstructor
public class ClassSessionController {

    private final ClassSessionService classSessionService;

    @GetMapping("/dates/{schoolClassId}")
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public List<LocalDateTime> getSessionsDates(@PathVariable Long schoolClassId) {
        return classSessionService.getSessions(schoolClassId).stream().map(ClassSession::getDate).toList();
    }
}
