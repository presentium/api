package ch.presentium.backend.business.service;

import ch.presentium.backend.business.model.schedule.ClassSession;
import ch.presentium.backend.business.model.schedule.SchoolClass;

/**
 * Service for managing class sessions.
 */
public interface ClassSessionService {
    /**
     * Create a new class session for a school class.
     *
     * @param schoolClass the school class
     * @return the created class session
     */
    ClassSession createSession(SchoolClass schoolClass);
}
