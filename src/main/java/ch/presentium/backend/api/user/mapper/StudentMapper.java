package ch.presentium.backend.api.user.mapper;

import ch.presentium.backend.api.user.model.StudentViewModel;
import ch.presentium.backend.api.user.request.StudentRequestBody;
import ch.presentium.backend.business.model.user.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface StudentMapper {
    StudentViewModel toViewModel(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "apiUser", ignore = true)
    @Mapping(target = "cardId", ignore = true)
    Student toModel(StudentRequestBody studentRequest);

    static String mapTeacherName(Student student) {
        return "%s %s".formatted(student.getFirstName(), student.getLastName());
    }
}
