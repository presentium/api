package ch.presentium.backend.api.schedule.schoolclass.mapper;

import ch.presentium.backend.api.schedule.schoolclass.model.SchoolClassViewModel;
import ch.presentium.backend.business.model.schedule.SchoolClass;
import org.mapstruct.Mapper;

@Mapper
public interface SchoolClassMapper {
    SchoolClassViewModel toViewModel(SchoolClass schoolClass);
}
