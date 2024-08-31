package ch.presentium.backend.api.reference;

import ch.presentium.backend.api.user.mapper.StudentMapper;
import ch.presentium.backend.api.user.mapper.TeacherMapper;
import ch.presentium.backend.business.model.user.Student;
import ch.presentium.backend.business.model.user.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { StudentMapper.class, TeacherMapper.class })
public interface ReferenceMapper {
    @Mapping(target = "name", source = ".")
    TeacherRef map(Teacher teacher);

    @Mapping(target = "name", source = ".")
    StudentRef map(Student student);
}
