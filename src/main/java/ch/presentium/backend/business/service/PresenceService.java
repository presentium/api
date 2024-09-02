package ch.presentium.backend.business.service;

import java.util.UUID;

/**
 * Service for managing presence information.
 */
public interface PresenceService {
    /**
     * Add a presence for a student in a class session.
     *
     * @param studentCardId  student card ID
     * @param classSessionId class session ID
     */
    void addPresence(String studentCardId, UUID classSessionId);
}
