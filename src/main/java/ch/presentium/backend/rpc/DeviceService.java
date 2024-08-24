package ch.presentium.backend.rpc;

import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

@Slf4j
@GrpcService
public class DeviceService extends DeviceServiceGrpc.DeviceServiceImplBase {

    @Override
    public void enterEventBus(EnterRequest request, StreamObserver<BusEvent> responseObserver) {
        log.info("Received event bus registration request");
    }
}
