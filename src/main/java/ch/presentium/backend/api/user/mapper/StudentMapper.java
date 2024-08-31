package ch.presentium.backend.api.user.mapper;

import ch.presentium.backend.business.model.user.Student;
import org.mapstruct.Mapper;

@Mapper
public interface StudentMapper {
    static String mapTeacherName(Student student) {
        return "%s %s".formatted(student.getFirstName(), student.getLastName());
    }
}
