package ch.presentium.backend.api.user.mapper;

import ch.presentium.backend.api.reference.ReferenceMapper;
import ch.presentium.backend.api.user.model.StudentViewModel;
import ch.presentium.backend.api.user.request.StudentRequestBody;
import ch.presentium.backend.business.model.user.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { ReferenceMapper.class })
public interface StudentMapper {
    @Mapping(target = "email", source = "apiUser.email")
    StudentViewModel toViewModel(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cardId", ignore = true)
    @Mapping(target = "apiUser", ignore = true)
    @Mapping(target = "schoolClasses", ignore = true)
    Student toModel(StudentRequestBody studentRequest);
}
