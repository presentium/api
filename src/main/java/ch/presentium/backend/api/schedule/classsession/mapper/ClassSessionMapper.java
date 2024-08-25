package ch.presentium.backend.api.schedule.classsession.mapper;

import ch.presentium.backend.api.schedule.schoolclass.model.SchoolClassViewModel;
import ch.presentium.backend.business.model.schedule.SchoolClass;
import org.mapstruct.Mapper;

@Mapper
public interface ClassSessionMapper {
    SchoolClassViewModel toViewModel(SchoolClass schoolClass);
}
