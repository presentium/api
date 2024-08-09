package ch.presentium.backend.business.model.academic;

import ch.presentium.backend.business.model.person.Teacher;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Entity
@Table(name = "class")
@Getter
public class Class {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "day_of_week", nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "start", nullable = false)
    private LocalDateTime start;

    @ManyToOne
    @JoinColumn(name = "room_id", nullable = false)
    private Room room;

    @ManyToOne
    @JoinColumn(name = "course_id", nullable = false)
    private Course course;

    @ManyToOne
    @JoinColumn(name = "teacher_id", nullable = false)
    private Teacher teacher;
}
