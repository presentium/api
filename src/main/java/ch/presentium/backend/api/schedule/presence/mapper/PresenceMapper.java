package ch.presentium.backend.api.schedule.presence.mapper;

import ch.presentium.backend.api.schedule.presence.model.PresenceViewModel;
import ch.presentium.backend.business.model.Presence;
import org.mapstruct.Mapper;

@Mapper
public interface PresenceMapper {
    PresenceViewModel toViewModel(Presence presence);
}
