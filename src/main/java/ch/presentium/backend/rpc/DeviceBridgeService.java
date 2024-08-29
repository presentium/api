package ch.presentium.backend.rpc;

import ch.presentium.backend.rpc.BusEvent.MessageType;
import java.time.Duration;
import java.util.HashSet;
import java.util.Set;
import java.util.function.UnaryOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.EmitFailureHandler;

@Slf4j
@Service
public class DeviceBridgeService {

    private final Set<String> devices = new HashSet<>();
    private final Sinks.Many<DeviceEvent> eventSink = Sinks.many().replay().limit(Duration.ofMinutes(5));

    Flux<BusEvent> subscribe(String deviceId) {
        log.info("Device {} connected to event bus", deviceId);
        devices.add(deviceId);
        return eventSink
            .asFlux()
            .filter(event -> event.deviceId().equals(deviceId))
            .map(DeviceEvent::event)
            .doOnTerminate(() -> {
                log.info("Device {} disconnected from event bus", deviceId);
                devices.remove(deviceId);
            });
    }

    public boolean isLoggedIn(String deviceId) {
        return devices.contains(deviceId);
    }

    public void send(String deviceId, EnrollStudentEvent event) {
        doSend(deviceId, builder -> builder.setType(MessageType.ENROLL_STUDENT).setEnrollStudent(event));
    }

    public void send(String deviceId, PresenceCheckEvent event) {
        doSend(deviceId, builder -> builder.setType(MessageType.PRESENCE_CHECK).setPresenceCheck(event));
    }

    private void doSend(String deviceId, UnaryOperator<BusEvent.Builder> customizer) {
        eventSink.emitNext(
            new DeviceEvent(deviceId, customizer.apply(BusEvent.newBuilder()).build()),
            EmitFailureHandler.FAIL_FAST
        );
    }

    record DeviceEvent(String deviceId, BusEvent event) {}
}
