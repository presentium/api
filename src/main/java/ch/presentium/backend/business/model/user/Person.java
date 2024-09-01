package ch.presentium.backend.business.model.user;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "person", uniqueConstraints = { @UniqueConstraint(name = "uk_api_user", columnNames = "api_user_fk") })
@Inheritance(strategy = InheritanceType.JOINED)
@Getter
@Setter
@Accessors(chain = true)
public abstract class Person {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @OneToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "api_user_fk", foreignKey = @ForeignKey(name = "fk_person_api_user"))
    private User apiUser;

    @Column(name = "name", nullable = false)
    private String fullName;
}
