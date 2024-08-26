package ch.presentium.backend.api;

import ch.presentium.backend.AbstractCommonTest;
import ch.presentium.backend.api.AbstractControllerIntegrationTest.ControllerContextConfiguration;
import ch.presentium.backend.business.service.security.JwtUserRegistration;
import ch.presentium.backend.configuration.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestEntityManager;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

/**
 * This class serves as the base context configuration for controller integration tests, usage is essentially the same as
 * {@link AbstractControllerTest}, but with the addition of a mocked database.
 */
@WebMvcTest
@AutoConfigureDataJpa
@AutoConfigureTestDatabase
@AutoConfigureTestEntityManager
@Import(ControllerContextConfiguration.class)
public abstract class AbstractControllerIntegrationTest extends AbstractCommonTest {

    @ComponentScan(
        basePackages = "ch.presentium.backend.api",
        includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*MapperImpl"),
        useDefaultFilters = false
    )
    @Import({ SecurityConfiguration.class, JwtUserRegistration.class })
    static class ControllerContextConfiguration {}

    @Autowired
    protected MockMvc api;
}
