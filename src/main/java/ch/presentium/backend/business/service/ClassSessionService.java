package ch.presentium.backend.business.service;

import ch.presentium.backend.business.model.schedule.ClassSession;
import ch.presentium.backend.business.model.schedule.SchoolClass;
import java.util.List;

public interface ClassSessionService {
    ClassSession createSession(SchoolClass schoolClass);
    List<ClassSession> getSessions(Long schoolClassId);
}
