package ch.presentium.backend.rpc;

import ch.presentium.backend.api.exception.DeviceDisconnectedException;
import ch.presentium.backend.rpc.BusEvent.MessageType;
import io.grpc.Context;
import java.time.Duration;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
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
    private final Map<String, DeviceMode> deviceModes = new HashMap<>();
    private final Sinks.Many<DeviceEvent> eventSink = Sinks.many().replay().limit(Duration.ofMinutes(5));

    Flux<BusEvent> subscribe(String deviceId) {
        devices.add(deviceId);
        deviceModes.put(deviceId, DeviceMode.IDLE);
        return eventSink
            .asFlux()
            .filter(event -> event.deviceId().equals(deviceId))
            .map(DeviceEvent::event)
            .takeWhile(e -> !Context.current().isCancelled())
            .doOnNext(event -> log.debug("Sending event {} to device {}", event.getType(), deviceId))
            .doFinally(type -> {
                log.info("Device {} disconnected from event bus ({})", deviceId, type);
                devices.remove(deviceId);
                deviceModes.remove(deviceId);
            });
    }

    void clearMode(String deviceId) {
        deviceModes.put(deviceId, DeviceMode.IDLE);
    }

    public boolean isLoggedIn(String deviceId) {
        return devices.contains(deviceId);
    }

    public DeviceMode getMode(String deviceId) {
        return deviceModes.get(deviceId);
    }

    public void send(String deviceId, EnrollStudentEvent event) {
        if (!devices.contains(deviceId)) {
            throw new DeviceDisconnectedException();
        }

        doSend(deviceId, builder -> builder.setType(MessageType.ENROLL_STUDENT).setEnrollStudent(event));
        deviceModes.put(deviceId, DeviceMode.STUDENT_ENROLL);
    }

    public void send(String deviceId, PresenceCheckEvent event) {
        if (!devices.contains(deviceId)) {
            throw new DeviceDisconnectedException();
        }

        doSend(deviceId, builder -> builder.setType(MessageType.PRESENCE_CHECK).setPresenceCheck(event));
        deviceModes.put(deviceId, DeviceMode.PRESENCE_CHECK);
    }

    private void doSend(String deviceId, UnaryOperator<BusEvent.Builder> customizer) {
        eventSink.emitNext(
            new DeviceEvent(deviceId, customizer.apply(BusEvent.newBuilder()).build()),
            EmitFailureHandler.FAIL_FAST
        );
    }

    record DeviceEvent(String deviceId, BusEvent event) {}
}
