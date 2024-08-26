package ch.presentium.backend.api.schedule.presence;

import ch.presentium.backend.api.schedule.presence.mapper.PresenceMapper;
import ch.presentium.backend.api.schedule.presence.model.PresenceViewModel;
import ch.presentium.backend.api.schedule.presence.model.PresenceViewPercentModel;
import ch.presentium.backend.business.service.PresenceService;
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
    List<PresenceViewModel> findAll(
        @RequestParam(required = false) UUID studentId,
        @RequestParam(required = false) Long classId
    ) {
        return presenceService.findAll().stream().map(presenceMapper::toViewModel).toList();
    }

    @GetMapping("/student/{studentId}")
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    PresenceViewModel findAllByStudentId(@PathVariable UUID studentId) {
        return presenceMapper.toViewModel(presenceService.findAllByStudentId(studentId));
    }

    @GetMapping("/percent/{classId}")
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    List<PresenceViewPercentModel> findAllPercent(@PathVariable Long classId) {
        return presenceService.calculateAttendancePercentage(classId);
    }
}
