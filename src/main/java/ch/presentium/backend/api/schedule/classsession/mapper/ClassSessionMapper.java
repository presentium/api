package ch.presentium.backend.api.schedule.classsession.mapper;

import ch.presentium.backend.api.schedule.classsession.model.ClassSessionViewModel;
import ch.presentium.backend.business.model.schedule.ClassSession;
import org.mapstruct.Mapper;

@Mapper
public interface ClassSessionMapper {
    ClassSessionViewModel toViewModel(ClassSession schoolClass);
}
