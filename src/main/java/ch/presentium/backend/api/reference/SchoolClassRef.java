package ch.presentium.backend.api.reference;

import java.time.LocalTime;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SchoolClassRef {

    Long id;
    String name;
    CourseRef course;
    LocalTime startTime;
    LocalTime endTime;
}
