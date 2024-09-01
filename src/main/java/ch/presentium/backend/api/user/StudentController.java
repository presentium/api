package ch.presentium.backend.api.user;

import ch.presentium.backend.annotation.security.IsTeacherUser;
import ch.presentium.backend.api.exception.ObjectNotFoundException;
import ch.presentium.backend.api.reference.ReferenceMapper;
import ch.presentium.backend.api.reference.StudentRef;
import ch.presentium.backend.api.user.mapper.StudentMapper;
import ch.presentium.backend.api.user.model.StudentViewModel;
import ch.presentium.backend.api.user.request.StudentRequestBody;
import ch.presentium.backend.business.repository.StudentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/students", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Students", description = "Consult and manage the student directory")
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;
    private final ReferenceMapper referenceMapper;
    private final StudentMapper studentMapper;

    @GetMapping(params = "refs")
    @IsTeacherUser
    @Operation(
        summary = "Get student references",
        parameters = { @Parameter(name = "refs", description = "Return only references") }
    )
    public List<StudentRef> getStudentRefs() {
        return studentRepository
            .findAll()
            .stream()
            .map(referenceMapper::map)
            .sorted(Comparator.comparing(StudentRef::name))
            .toList();
    }

    @GetMapping
    @IsTeacherUser
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public List<StudentRef> getStudents() {
        return studentRepository.findAll().stream().map(referenceMapper::map).toList();
    }

    @GetMapping("/{id}")
    @IsTeacherUser
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public StudentViewModel getStudent(@PathVariable @NotNull UUID id) {
        return studentRepository
            .findById(id)
            .map(studentMapper::toViewModel)
            .orElseThrow(() -> ObjectNotFoundException.forStudent(id));
    }

    @PostMapping
    @IsTeacherUser
    @Transactional(rollbackFor = Throwable.class)
    @ResponseStatus(HttpStatus.CREATED)
    public void addStudent(@RequestBody @Valid StudentRequestBody studentBody) {
        studentRepository.save(studentMapper.toModel(studentBody));
    }

    @DeleteMapping("/{id}")
    @IsTeacherUser
    @Transactional(rollbackFor = Throwable.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteStudent(@PathVariable @NotNull UUID id) {
        studentRepository.deleteById(id);
    }
}
