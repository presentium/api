package ch.presentium.backend.business.model.academic;

import ch.presentium.backend.business.model.person.Teacher;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Entity
@Table(name = "class")
@Getter
public class Class {
    @Id
    private String id;
    private DayOfWeek dayOfWeek;
    private LocalDateTime start;
    @ManyToOne
    private Room room;
    @ManyToOne
    private Course course;
    @ManyToOne
    private Teacher teacher;
}
