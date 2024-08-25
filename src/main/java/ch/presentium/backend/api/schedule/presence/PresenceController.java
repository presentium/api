package ch.presentium.backend.api.schedule.presence;

import ch.presentium.backend.api.schedule.presence.mapper.PresenceMapper;
import ch.presentium.backend.api.schedule.presence.model.PresenceViewModel;
import ch.presentium.backend.business.service.PresenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/presence")
public class PresenceController {

    private final PresenceService presenceService;
    private final PresenceMapper presenceMapper;

    @Autowired
    public PresenceController(PresenceService presenceService, PresenceMapper presenceMapper) {
        this.presenceService = presenceService;
        this.presenceMapper = presenceMapper;
    }

    @GetMapping("/all")
    List<PresenceViewModel> findAll() {
        return presenceService.findAll().stream().map(presenceMapper::toViewModel).toList();
    }
}
