package ch.presentium.backend.api.schedule.course.mapper;

import ch.presentium.backend.api.schedule.course.model.CourseViewModel;
import ch.presentium.backend.business.model.schedule.Course;
import org.mapstruct.Mapper;

@Mapper
public interface CourseMapper {
    CourseViewModel toViewModel(Course course);
}
