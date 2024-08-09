package ch.presentium.backend.business.model.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "api_user")
@Getter
@Setter
@Accessors(chain = true)
public class User {

    // JWT claim: sub
    @Id
    @Column(name = "id", nullable = false)
    private String id;

    /**
     * In the JWT configuration we use, the subject claim will use a UUID as the value, the username is given in a
     * specific profile claim value. The username is not necessarily immutable, but the subject UUID should be.
     */
    // JWT claim: username
    @NaturalId
    @Column(name = "subject", nullable = false)
    private String subject;

    // JWT claim: email
    @Column(name = "email", nullable = false)
    private String email;

    // JWT claim: name
    @Column(name = "display_name", nullable = false)
    private String displayName;
}
