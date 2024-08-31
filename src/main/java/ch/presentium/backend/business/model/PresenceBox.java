package ch.presentium.backend.business.model;

import ch.presentium.backend.business.model.user.Teacher;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "presence_box")
@Getter
@Setter
@Accessors(chain = true)
public class PresenceBox implements UserDetails {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    /**
     * The certificate common name that the box provided upon registration.
     */
    @NaturalId
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * The teacher who is responsible for the presence box, null if free to be assigned.
     */
    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "teacher_fk", foreignKey = @ForeignKey(name = "fk_box_teacher"))
    private Teacher teacher;

    public PresenceBox setTeacher(Teacher teacher) {
        this.teacher = teacher;
        if (teacher != null) {
            teacher.getDevices().add(this);
        }

        return this;
    }

    public PresenceBox removeTeacher() {
        if (teacher != null) {
            teacher.getDevices().remove(this);
            teacher = null;
        }

        return this;
    }

    @Override
    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_DEVICE"));
    }

    @Override
    @Transient
    public String getUsername() {
        return name;
    }

    @Override
    @Transient
    public String getPassword() {
        return null;
    }
}
