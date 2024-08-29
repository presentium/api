package ch.presentium.backend.api.schedule.student.mapper;

import ch.presentium.backend.api.schedule.student.model.StudentViewModel;
import ch.presentium.backend.api.schedule.student.request.StudentRequest;
import ch.presentium.backend.business.model.user.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface StudentMapper {
    StudentViewModel toViewModel(Student student);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "apiUser", ignore = true)
    Student toModel(StudentRequest studentRequest);
}
