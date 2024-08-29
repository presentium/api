package ch.presentium.backend.api.mapping.impl;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import ch.presentium.backend.business.model.PresenceBox;
import ch.presentium.backend.rpc.DeviceBridgeService;
import ch.presentium.backend.rpc.DeviceMode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class MappingContextImplTest {

    private @Mock DeviceBridgeService deviceBridgeService;

    @InjectMocks
    private MappingContextImpl mappingContext;

    @Test
    void isDeviceConnected() {
        var device = new PresenceBox().setName("device");
        when(deviceBridgeService.isLoggedIn(device.getName())).thenReturn(true);
        assertTrue(mappingContext.isDeviceConnected(device));
    }

    @Test
    void getDeviceMode() {
        var device = new PresenceBox().setName("device");
        when(deviceBridgeService.getMode(device.getName())).thenReturn(DeviceMode.IDLE);
        assertSame(DeviceMode.IDLE, mappingContext.getDeviceMode(device));
    }
}
