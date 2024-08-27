package ch.presentium.backend.api.schedule.presence;

import ch.presentium.backend.api.schedule.presence.mapper.PresenceMapper;
import ch.presentium.backend.api.schedule.presence.model.PresenceViewModel;
import ch.presentium.backend.api.schedule.presence.model.PresenceViewPercentModel;
import ch.presentium.backend.business.service.PresenceService;
import ch.presentium.backend.business.utils.DateRange;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/v1/presences")
@RequiredArgsConstructor
public class PresenceController {

    private final PresenceService presenceService;
    private final PresenceMapper presenceMapper;

    @GetMapping
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public List<PresenceViewPercentModel> findAll(
        @RequestParam Long classId,
        @RequestParam DateRange dateRange,
        @RequestParam(required = false) UUID studentId
        ) {
        return presenceService.calculateAttendancePercentage(classId, dateRange, studentId);
    }
}
