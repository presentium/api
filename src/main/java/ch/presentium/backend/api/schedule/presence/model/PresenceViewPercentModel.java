package ch.presentium.backend.api.schedule.presence.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
public class PresenceViewPercentModel extends PresenceViewModel {
    private double presencePercent;
}
