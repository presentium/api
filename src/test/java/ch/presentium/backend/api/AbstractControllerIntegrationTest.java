package ch.presentium.backend.api;

import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;

/**
 * This class serves as the base context configuration for controller integration tests, usage is essentially the same as
 * {@link AbstractControllerTest}, but with the addition of a mocked database.
 */
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
public abstract class AbstractControllerIntegrationTest extends AbstractControllerTest {}
