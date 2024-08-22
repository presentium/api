package ch.presentium.backend.business.model.user;

import ch.presentium.backend.business.model.schedule.Class;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "teacher")
@PrimaryKeyJoinColumn(name = "person_fk", foreignKey = @ForeignKey(name = "fk_teacher_person"))
@Getter
@Setter
@Accessors(chain = true)
public class Teacher extends Person {

    @OneToMany(mappedBy = "teacher")
    private List<Class> classes;
}
