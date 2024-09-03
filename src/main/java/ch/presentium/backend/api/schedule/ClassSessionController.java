package ch.presentium.backend.api.schedule;

import ch.presentium.backend.business.model.schedule.ClassSession;
import ch.presentium.backend.business.repository.ClassSessionRepository;
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
@RequestMapping(path = "/v1/school-classes/{schoolClassId}/sessions", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Class session management", description = "Manage class sessions")
@RequiredArgsConstructor
public class ClassSessionController {

    private final ClassSessionRepository classSessionRepository;

    @GetMapping("/dates")
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public List<LocalDateTime> getSessionsDates(@PathVariable Long schoolClassId) {
        return classSessionRepository.findBySchoolClassId(schoolClassId).stream().map(ClassSession::getDate).toList();
    }
}
