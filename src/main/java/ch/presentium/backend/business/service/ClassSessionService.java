package ch.presentium.backend.business.service;

import ch.presentium.backend.business.model.schedule.ClassSession;
import ch.presentium.backend.business.model.schedule.SchoolClass;

public interface ClassSessionService {
    ClassSession createSession(SchoolClass schoolClass);
}
