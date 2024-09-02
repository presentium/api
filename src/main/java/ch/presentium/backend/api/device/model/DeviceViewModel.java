package ch.presentium.backend.api.device.model;

import ch.presentium.backend.api.reference.TeacherRef;
import ch.presentium.backend.rpc.DeviceMode;
import java.util.UUID;
import lombok.Builder;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@Builder
public class DeviceViewModel {

    private final UUID id;
    private final String commonName;

    private final boolean connected;
    private final DeviceMode mode;

    private final @Nullable TeacherRef teacher;
}
