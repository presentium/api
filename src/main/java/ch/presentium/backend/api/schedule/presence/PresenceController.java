package ch.presentium.backend.api.schedule.presence;

import ch.presentium.backend.api.schedule.presence.model.PresenceViewModel;
import ch.presentium.backend.api.types.daterange.DateRange;
import ch.presentium.backend.business.service.PresenceService;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/presences")
@RequiredArgsConstructor
public class PresenceController {

    private final PresenceService presenceService;

    @GetMapping
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public List<PresenceViewModel> findAll(
        @RequestParam(required = false) Long classId,
        @RequestParam(required = false) UUID studentId,
        DateRange dateRange
    ) {
        return presenceService.calculateAttendance(classId, dateRange, studentId);
    }
}
