package ch.presentium.backend.api.schedule.student;

import ch.presentium.backend.api.schedule.student.mapper.StudentMapper;
import ch.presentium.backend.api.schedule.student.model.StudentViewModel;
import ch.presentium.backend.api.schedule.student.request.StudentRequest;
import ch.presentium.backend.business.service.StudentService;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/student")
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @Autowired
    public StudentController(StudentService studentService, StudentMapper studentMapper) {
        this.studentService = studentService;
        this.studentMapper = studentMapper;
    }

    @GetMapping("/all")
    @Transactional(readOnly = true)
    public List<StudentViewModel> getAllStudents() {
        return studentService.findAll().stream().map(studentMapper::toViewModel).toList();
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true)
    public StudentViewModel getStudent(@PathVariable @NotNull UUID id) {
        return studentMapper.toViewModel(studentService.findById(id));
    }

    @PostMapping("/add")
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudent(@RequestBody StudentRequest studentBody) {
        studentService.addStudent(studentBody.getFirstName(), studentBody.getLastName(), studentBody.getEmail());
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable @NotNull UUID id) {
        studentService.deleteStudent(id);
    }
}
