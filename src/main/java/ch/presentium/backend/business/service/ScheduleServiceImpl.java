package ch.presentium.backend.business.service;

import ch.presentium.backend.business.model.schedule.Course;
import ch.presentium.backend.business.model.schedule.Room;
import ch.presentium.backend.business.model.schedule.SchoolClass;
import ch.presentium.backend.business.model.schedule.Semester;
import ch.presentium.backend.business.model.user.Teacher;
import ch.presentium.backend.business.repository.CourseRepository;
import ch.presentium.backend.business.repository.RoomRepository;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.Component;
import net.fortuna.ical4j.model.component.VEvent;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {

    private final CourseRepository courseRepository;
    private final RoomRepository roomRepository;

    @Override
    public List<Course> importCourses(Teacher teacher, Calendar calendar) {
        var calId = calendar
            .getProperty("X-HEIGVD-SCHEDULEID")
            .map(val -> val.getValue().split("_"))
            .filter(split -> split.length > 3)
            .orElseThrow(() -> new IllegalArgumentException("Calendar does not have a valid schedule ID"));

        Semester semester = Semester.valueOf(calId[calId.length - 3]);
        Year year = Year.of(Integer.parseInt(calId[calId.length - 2]));

        return calendar
            .getComponents(Component.VEVENT)
            .stream()
            .map(VEvent.class::cast)
            .collect(
                Collectors.collectingAndThen(
                    Collectors.groupingBy(event -> CourseName.from(event.getSummary().orElseThrow().getValue()).course()
                    ),
                    map -> map.entrySet().stream()
                )
            )
            .map(entry -> importCourse(teacher, semester, year, entry.getKey(), entry.getValue()))
            .toList();
    }

    private Course importCourse(Teacher teacher, Semester semester, Year year, String courseName, List<VEvent> events) {
        var course = getCourse(courseName, semester, year);

        events.forEach(event -> {
            CourseName name = CourseName.from(event.getSummary().orElseThrow().getValue());

            course.addClass(importClass(teacher, name, event));
        });

        return course;
    }

    private SchoolClass importClass(Teacher teacher, CourseName courseName, VEvent event) {
        var start = LocalDateTime.from(event.getDateTimeStart().orElseThrow().getDate());
        var end = LocalDateTime.from(event.getDateTimeEnd().orElseThrow().getDate());

        return new SchoolClass()
            .setGroup(courseName.group())
            .setName(courseName.period())
            .setDayOfWeek(DayOfWeek.from(start))
            .setStart(start.toLocalTime())
            .setEnd(end.toLocalTime())
            .setRoom(getRoom(event.getLocation().orElseThrow().getValue()))
            .setTeacher(teacher);
    }

    private Course getCourse(String name, Semester semester, Year year) {
        return courseRepository
            .findByNameAndSemesterAndYear(name, semester, year)
            .orElseGet(() -> courseRepository.save(new Course().setName(name).setSemester(semester).setYear(year)));
    }

    private Room getRoom(String name) {
        return roomRepository.findByName(name).orElseGet(() -> roomRepository.save(new Room().setName(name)));
    }

    private record CourseName(String course, String group, String period) {
        public static CourseName from(String summary) {
            var split = summary.split("-");
            if (split.length < 3) {
                throw new IllegalArgumentException("Invalid course name: " + summary);
            }

            return new CourseName(
                Arrays.stream(split, 0, split.length - 2).collect(Collectors.joining("-")),
                split[split.length - 2],
                split[split.length - 1]
            );
        }
    }
}
