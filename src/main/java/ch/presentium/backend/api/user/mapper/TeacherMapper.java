package ch.presentium.backend.api.user.mapper;

import ch.presentium.backend.business.model.user.Teacher;
import org.mapstruct.Mapper;

@Mapper
public interface TeacherMapper {
    static String mapTeacherName(Teacher teacher) {
        return "%s %s".formatted(teacher.getFirstName(), teacher.getLastName());
    }
}
