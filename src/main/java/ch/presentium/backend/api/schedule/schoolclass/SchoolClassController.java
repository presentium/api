package ch.presentium.backend.api.schedule.schoolclass;

import ch.presentium.backend.api.schedule.schoolclass.mapper.SchoolClassMapper;
import ch.presentium.backend.api.schedule.schoolclass.model.SchoolClassViewModel;
import ch.presentium.backend.business.repository.SchoolClassRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/v1/classes", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Class management", description = "Operations for managing classes")
@RequiredArgsConstructor
public class SchoolClassController {

    private final SchoolClassRepository classRepository;
    private final SchoolClassMapper classMapper;

    @GetMapping
    @Transactional(readOnly = true, rollbackFor = Throwable.class)
    public List<SchoolClassViewModel> getAllClasses() {
        return classRepository.findAll().stream().map(classMapper::toViewModel).toList();
    }
}
