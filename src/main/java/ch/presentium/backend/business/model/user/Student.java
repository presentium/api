package ch.presentium.backend.business.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Entity
@Table(name = "student")
@PrimaryKeyJoinColumn(name = "person_fk", foreignKey = @ForeignKey(name = "fk_student_person"))
@Getter
@Setter
@Accessors(chain = true)
public class Student extends Person {}
