package ch.presentium.backend.api.student;

import ch.presentium.backend.business.model.user.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<StudentViewModel> findAllView() {
        return studentRepository.findAllView();
    }

    public void addStudent(String firstName, String lastName, String email) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setEmail(email);
        studentRepository.save(student);
    }
}
