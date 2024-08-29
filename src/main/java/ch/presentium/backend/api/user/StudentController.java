package ch.presentium.backend.api.user;

import ch.presentium.backend.annotation.security.IsTeacherUser;
import ch.presentium.backend.api.reference.ReferenceMapper;
import ch.presentium.backend.api.reference.StudentRef;
import ch.presentium.backend.business.repository.StudentRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.Comparator;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/v1/students", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Students", description = "Consult the student directory")
@RequiredArgsConstructor
public class StudentController {

    private final StudentRepository studentRepository;
    private final ReferenceMapper referenceMapper;

    @GetMapping(params = "refs")
    @IsTeacherUser
    @Operation(
        summary = "Get student references",
        parameters = { @Parameter(name = "refs", description = "Return only references", required = true) }
    )
    public List<StudentRef> getStudentRefs() {
        return studentRepository
            .findAll()
            .stream()
            .map(referenceMapper::map)
            .sorted(Comparator.comparing(StudentRef::name))
            .toList();
    }
}
