package ch.presentium.backend.api.schedule;

import ch.presentium.backend.api.common.DateRange;
import ch.presentium.backend.api.schedule.model.PresenceViewModel;
import ch.presentium.backend.business.repository.PresenceRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/presences")
@Tag(name = "School class presence management", description = "Manage school classes and their sessions")
@RequiredArgsConstructor
public class PresenceController {

    private final PresenceRepository presenceRepository;

    @GetMapping
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public List<PresenceViewModel> getPresences(
        @RequestParam(required = false) UUID studentId,
        @RequestParam(required = false) Long schoolClassId,
        DateRange dateRange
    ) {
        return presenceRepository.calculateAttendance(schoolClassId, dateRange, studentId);
    }
}
