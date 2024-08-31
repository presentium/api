package ch.presentium.backend.business.service;

import ch.presentium.backend.api.exception.ObjectNotFoundException;
import ch.presentium.backend.business.repository.StudentRepository;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final StudentRepository studentRepository;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void enrollStudentCard(UUID studentId, String cardId) {
        log.info("Enrolling student {} with card {}", studentId, cardId);
        var student = studentRepository
            .findById(studentId)
            .orElseThrow(() -> ObjectNotFoundException.forStudent(studentId));

        student.setCardId(cardId);
    }
}
