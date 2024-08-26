package ch.presentium.backend.business.model.schedule;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.Year;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * For example, PDG 2024 is a course
 */
@Entity
@Table(
    name = "course",
    uniqueConstraints = { @UniqueConstraint(name = "uk_course_name", columnNames = { "name", "year", "semester" }) }
)
@Getter
@Setter
@Accessors(chain = true)
public class Course {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "semester", nullable = false)
    private Semester semester;

    @Column(name = "year", nullable = false)
    private Year year;

    @OneToMany(mappedBy = "course")
    private List<SchoolClass> classes;
}
