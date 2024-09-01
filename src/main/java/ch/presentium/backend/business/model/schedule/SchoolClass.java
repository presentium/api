package ch.presentium.backend.business.model.schedule;

import ch.presentium.backend.business.model.user.Student;
import ch.presentium.backend.business.model.user.Teacher;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * For example, PDG(2024)-A-C1 is a class running on dow 1 at 8:15
 */
@Entity
@Table(
    name = "school_class",
    uniqueConstraints = {
        @UniqueConstraint(name = "uk_school_class_name", columnNames = { "group", "name", "course_fk" }),
    }
)
@Getter
@Setter
@Accessors(chain = true)
public class SchoolClass {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "class_group", nullable = false)
    private String group;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = false)
    @JoinColumn(name = "course_fk", foreignKey = @ForeignKey(name = "fk_class_course"), nullable = false)
    private Course course;

    @Column(name = "day_of_week", nullable = false)
    private DayOfWeek dayOfWeek;

    @Column(name = "dt_start", nullable = false)
    private LocalTime start;

    @Column(name = "dt_end", nullable = false)
    private LocalTime end;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = false)
    @JoinColumn(name = "room_fk", foreignKey = @ForeignKey(name = "fk_class_room"), nullable = false)
    private Room room;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST }, optional = false)
    @JoinColumn(name = "teacher_fk", foreignKey = @ForeignKey(name = "fk_class_teacher"), nullable = false)
    private Teacher teacher;

    @OneToMany(mappedBy = "schoolClass", cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    private Set<ClassSession> sessions;

    @ManyToMany(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinTable(
        name = "class_student",
        joinColumns = @JoinColumn(
            name = "class_fk",
            foreignKey = @ForeignKey(name = "fk_class_student"),
            nullable = false
        ),
        inverseJoinColumns = @JoinColumn(
            name = "student_fk",
            foreignKey = @ForeignKey(name = "fk_student_class"),
            nullable = false
        )
    )
    private Set<Student> students;

    public SchoolClass addSession(ClassSession session) {
        if (sessions == null) {
            sessions = new HashSet<>();
        }

        sessions.add(session);
        session.setSchoolClass(this);
        return this;
    }

    public SchoolClass addStudent(Student student) {
        if (students == null) {
            students = new HashSet<>();
        }
        students.add(student);

        if (student.getSchoolClasses() == null) {
            student.setSchoolClasses(new HashSet<>());
        }
        student.getSchoolClasses().add(this);

        return this;
    }
}
