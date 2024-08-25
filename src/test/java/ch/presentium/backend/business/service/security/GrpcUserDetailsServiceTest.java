package ch.presentium.backend.business.service.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.assertArg;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import ch.presentium.backend.business.model.PresenceBox;
import ch.presentium.backend.business.repository.PresenceBoxRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class GrpcUserDetailsServiceTest {

    private @Mock PresenceBoxRepository presenceBoxRepository;

    private @InjectMocks GrpcUserDetailsService grpcUserDetailsService;

    @Test
    void missingBox_createsBox() {
        grpcUserDetailsService.loadUserByUsername("super-cattle.devices.presentium.ch");
        verify(presenceBoxRepository).findByName("super-cattle.devices.presentium.ch");
        verify(presenceBoxRepository).save(
            assertArg(box -> {
                assertEquals("super-cattle.devices.presentium.ch", box.getName());
            })
        );
    }

    @Test
    void existingBox_updates() {
        var box = new PresenceBox();
        box.setName("super-cattle.devices.presentium.ch");
        when(presenceBoxRepository.findByName("super-cattle.devices.presentium.ch")).thenReturn(Optional.of(box));

        grpcUserDetailsService.loadUserByUsername("super-cattle.devices.presentium.ch");

        verify(presenceBoxRepository).save(
            assertArg(user -> {
                assertSame(box, user);
            })
        );
    }
}
