package ch.presentium.backend.api.device.mapper;

import ch.presentium.backend.api.device.model.DeviceViewModel;
import ch.presentium.backend.api.mapping.MappingContext;
import ch.presentium.backend.api.reference.ReferenceMapper;
import ch.presentium.backend.business.model.PresenceBox;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { ReferenceMapper.class })
public interface DeviceMapper {
    @Mapping(target = "commonName", source = "name")
    @Mapping(target = "connected", expression = "java(ctx.isDeviceConnected(device))")
    @Mapping(target = "mode", expression = "java(ctx.getDeviceMode(device))")
    DeviceViewModel map(PresenceBox device, @Context MappingContext ctx);
}
