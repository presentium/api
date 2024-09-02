package ch.presentium.backend.api.schedule.room.model;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RoomViewModel {

    @NotNull private final String name;
}
