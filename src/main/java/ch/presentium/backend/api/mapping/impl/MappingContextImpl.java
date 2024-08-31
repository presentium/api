package ch.presentium.backend.api.mapping.impl;

import ch.presentium.backend.api.mapping.MappingContext;
import ch.presentium.backend.business.model.PresenceBox;
import ch.presentium.backend.rpc.DeviceBridgeService;
import ch.presentium.backend.rpc.DeviceMode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MappingContextImpl implements MappingContext {

    private final DeviceBridgeService deviceBridgeService;

    @Override
    public boolean isDeviceConnected(PresenceBox device) {
        return deviceBridgeService.isLoggedIn(device.getName());
    }

    @Override
    public DeviceMode getDeviceMode(PresenceBox device) {
        return deviceBridgeService.getMode(device.getName());
    }
}
