package ch.presentium.backend.api.reference;

import ch.presentium.backend.api.schedule.mapper.SchoolClassMapper;
import ch.presentium.backend.business.model.schedule.SchoolClass;
import ch.presentium.backend.business.model.user.Student;
import ch.presentium.backend.business.model.user.Teacher;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = { SchoolClassMapper.class })
public interface ReferenceMapper {
    @Mapping(target = "name", source = "fullName")
    TeacherRef map(Teacher teacher);

    @Mapping(target = "name", source = "fullName")
    StudentRef map(Student student);

    @Mapping(target = "name", source = ".", qualifiedByName = SchoolClassMapper.HELPER_NAME)
    SchoolClassRef map(SchoolClass schoolClass);
}
