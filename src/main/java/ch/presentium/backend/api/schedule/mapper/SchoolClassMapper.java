package ch.presentium.backend.api.schedule.mapper;

import ch.presentium.backend.api.reference.ReferenceMapper;
import ch.presentium.backend.api.schedule.model.SchoolClassViewModel;
import ch.presentium.backend.business.model.schedule.SchoolClass;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(uses = { ReferenceMapper.class })
public interface SchoolClassMapper {
    String HELPER_NAME = "SchoolClassMapper/mapName";

    @Mapping(target = "name", source = ".", qualifiedByName = HELPER_NAME)
    @Mapping(target = "room", source = "room.name")
    SchoolClassViewModel map(SchoolClass schoolClass);

    @Named(HELPER_NAME)
    static String mapName(SchoolClass schoolClass) {
        return "%s-%s-%s".formatted(schoolClass.getCourse().getName(), schoolClass.getGroup(), schoolClass.getName());
    }

    SchoolClass toModel(SchoolClassViewModel schoolClassViewModel);
}
