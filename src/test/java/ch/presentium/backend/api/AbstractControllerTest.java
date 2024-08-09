package ch.presentium.backend.api;

import ch.presentium.backend.AbstractCommonTest;
import ch.presentium.backend.api.AbstractControllerTest.ControllerContextConfiguration;
import ch.presentium.backend.configuration.SecurityConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

/**
 * This class serves as the base context configuration for controller unit tests, where only the
 * mappers are expected to be defined in the context. Usage:
 *
 * <pre>{@code
 * @WebMvcTest(MyController.class)
 * class MyControllerTest extends AbstractControllerTest {
 *
 *   // [...]
 * }
 * }</pre>
 */
@WebMvcTest
@Import(ControllerContextConfiguration.class)
public abstract class AbstractControllerTest extends AbstractCommonTest {

    @ComponentScan(
        basePackages = "ch.presentium.backend.api",
        includeFilters = @ComponentScan.Filter(type = FilterType.REGEX, pattern = ".*MapperImpl"),
        useDefaultFilters = false
    )
    @Import(SecurityConfiguration.class)
    static class ControllerContextConfiguration {}

    @Autowired
    protected MockMvc api;
}
