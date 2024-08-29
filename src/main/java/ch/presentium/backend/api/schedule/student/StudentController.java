package ch.presentium.backend.api.schedule.student;

import ch.presentium.backend.annotation.security.IsTeacherUser;
import ch.presentium.backend.api.exception.ObjectNotFoundException;
import ch.presentium.backend.api.schedule.student.mapper.StudentMapper;
import ch.presentium.backend.api.schedule.student.model.StudentViewModel;
import ch.presentium.backend.api.schedule.student.request.StudentRequest;
import ch.presentium.backend.business.service.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/v1/students", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Student management", description = "Operations for managing students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;
    private final StudentMapper studentMapper;

    @GetMapping
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public List<StudentViewModel> getAllStudents() {
        return studentService.findAll().stream().map(studentMapper::toViewModel).toList();
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public StudentViewModel getStudent(@PathVariable @NotNull UUID id) {
        return studentService
            .findById(id)
            .map(studentMapper::toViewModel)
            .orElseThrow(() -> ObjectNotFoundException.forStudent(id));
    }

    @PostMapping
    @IsTeacherUser
    @Transactional(rollbackFor = Throwable.class)
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudent(@RequestBody @Valid StudentRequest studentBody) {
        studentService.addStudent(studentMapper.toModel(studentBody));
    }

    @DeleteMapping("/{id}")
    @IsTeacherUser
    @Transactional(rollbackFor = Throwable.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable @NotNull UUID id) {
        studentService.deleteStudent(id);
    }
}
