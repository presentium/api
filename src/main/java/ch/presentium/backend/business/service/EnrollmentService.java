package ch.presentium.backend.business.service;

import java.util.UUID;

/**
 * Service for enrolling students.
 */
public interface EnrollmentService {
    /**
     * Enroll a student with a card. Meant to be used by readers.
     *
     * @param studentId the student ID
     * @param cardId the card ID
     */
    void enrollStudentCard(UUID studentId, String cardId);
}
