package ch.presentium.backend.api.schedule.mapper;

import ch.presentium.backend.api.schedule.model.StudentViewModel;
import ch.presentium.backend.api.schedule.request.StudentRequest;
import ch.presentium.backend.business.model.user.Student;
import org.mapstruct.Mapper;

@Mapper
public interface StudentMapper {
    StudentViewModel toViewModel(Student student);
    Student toModel(StudentRequest studentRequest);
}
