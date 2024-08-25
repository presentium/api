package ch.presentium.backend.api.student.mapper;

import ch.presentium.backend.api.student.model.StudentViewModel;
import ch.presentium.backend.business.model.user.Student;
import org.mapstruct.Mapper;

@Mapper
public interface StudentMapper {
    StudentViewModel toViewModel(Student student);
}
