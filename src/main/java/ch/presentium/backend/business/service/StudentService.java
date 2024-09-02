package ch.presentium.backend.business.service;

import ch.presentium.backend.business.model.user.Student;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public interface StudentService {
    /**
     * Find all students.
     * @return A list of all students.
     */
    List<Student> findAll();

    /**
     * Find a student by ID.
     * @param id The ID of the student to find.
     * @return The student with the given ID, or an empty optional if no such student exists.
     */
    Optional<Student> findById(UUID id);

    /**
     * Add a student.
     * @param student The student to add.
     */
    void addStudent(Student student);

    /**
     * Delete a student by ID.
     * @param id The ID of the student to delete.
     */
    void deleteStudent(UUID id);
}
