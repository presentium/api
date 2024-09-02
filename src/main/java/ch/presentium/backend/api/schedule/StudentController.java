package ch.presentium.backend.api.schedule;

import ch.presentium.backend.api.schedule.mapper.StudentMapper;
import ch.presentium.backend.api.schedule.model.StudentViewModel;
import ch.presentium.backend.api.schedule.request.StudentRequest;
import ch.presentium.backend.business.service.StudentService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@Slf4j
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

    @PostMapping
    @Transactional
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudent(@RequestBody @Valid StudentRequest studentBody) {
        studentService.addStudent(studentMapper.toModel(studentBody));
    }

    @GetMapping("/{id}")
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public StudentViewModel getStudent(@PathVariable @NotNull UUID id) {
        return studentMapper.toViewModel(studentService.findById(id).orElseThrow());
    }

    @DeleteMapping("/{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable @NotNull UUID id) {
        studentService.deleteStudent(id);
    }
}
