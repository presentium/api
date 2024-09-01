package ch.presentium.backend.business.model.user;

import ch.presentium.backend.business.model.schedule.SchoolClass;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.Set;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.springframework.lang.Nullable;

@Entity
@Table(
    name = "student",
    uniqueConstraints = { @UniqueConstraint(name = "uk_student_card_id", columnNames = { "card_id" }) }
)
@PrimaryKeyJoinColumn(name = "person_fk", foreignKey = @ForeignKey(name = "fk_student_person"))
@Getter
@Setter
@Accessors(chain = true)
public class Student extends Person {

    @Nullable @Column(name = "card_id")
    private String cardId;

    @ManyToMany(mappedBy = "students")
    private Set<SchoolClass> schoolClasses;
}
