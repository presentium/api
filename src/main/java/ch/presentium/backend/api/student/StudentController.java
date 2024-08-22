package ch.presentium.backend.api.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@RestController
@RequestMapping("/v1/student")
public class StudentController {
    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    @Transactional(readOnly = true)
    public List<StudentViewModel> getAllStudents() {
        return studentService.findAllView();
    }

    @PostMapping("/add")
    @Transactional
    public void addStudent(@RequestBody StudentRequest studentBody){
        studentService.addStudent(
                studentBody.getFirstName(),
                studentBody.getLastName(),
                studentBody.getEmail()
        );
    }
}
