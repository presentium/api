package ch.presentium.backend.api.schedule.teacher.mapper;

import ch.presentium.backend.api.schedule.teacher.model.TeacherPresenceModelView;
import ch.presentium.backend.business.model.user.Teacher;
import org.mapstruct.Mapper;

@Mapper
public interface TeacherMapper {
    TeacherPresenceModelView toViewModel(Teacher teacherPresenceModelView);
}
