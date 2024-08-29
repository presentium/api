package ch.presentium.backend.api.mapping;

import ch.presentium.backend.business.model.PresenceBox;
import ch.presentium.backend.rpc.DeviceMode;

public interface MappingContext {
    boolean isDeviceConnected(PresenceBox device);
    DeviceMode getDeviceMode(PresenceBox device);
}
