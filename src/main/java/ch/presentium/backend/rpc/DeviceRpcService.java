package ch.presentium.backend.rpc;

import ch.presentium.backend.business.service.EnrollmentService;
import ch.presentium.backend.business.service.PresenceService;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.security.authentication.X509CertificateAuthentication;
import net.devh.boot.grpc.server.service.GrpcService;
import org.springframework.security.core.context.SecurityContextHolder;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Slf4j
@GrpcService
@RequiredArgsConstructor
public class DeviceRpcService extends ReactorDeviceServiceGrpc.DeviceServiceImplBase {

    private final DeviceBridgeService deviceService;
    private final EnrollmentService enrollmentService;
    private final PresenceService presenceService;

    @Override
    public Flux<BusEvent> enterEventBus(EnterRequest request) {
        var certificate = (X509CertificateAuthentication) SecurityContextHolder.getContext().getAuthentication();

        log.info("Device {} connected to event bus", certificate.getName());
        return deviceService.subscribe(certificate.getName());
    }

    @Override
    public Mono<GenericResponse> studentEnrolled(Mono<EnrolledStudent> request) {
        return request
            .publishOn(Schedulers.boundedElastic())
            .map(req -> {
                enrollmentService.enrollStudentCard(UUID.fromString(req.getStudentId()), req.getCardId());
                return GenericResponse.newBuilder().setStatus("OK").setMessage("Student enrolled").build();
            });
    }

    @Override
    public Mono<GenericResponse> studentPresence(Mono<PresenceStudent> request) {
        return request
            .publishOn(Schedulers.boundedElastic())
            .map(req -> {
                presenceService.addPresence(req.getCardId(), UUID.fromString(req.getSessionId()));
                return GenericResponse.newBuilder().setStatus("OK").setMessage("Student presence verified").build();
            });
    }
}
