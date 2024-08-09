package ch.presentium.backend.api.version;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ch.presentium.backend.api.AbstractControllerTest;
import ch.presentium.backend.configuration.model.BuildInfoProperties;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

@WebMvcTest(VersionController.class)
class VersionControllerTest extends AbstractControllerTest {

    @MockBean
    private BuildInfoProperties buildInfoProperties;

    @Nested
    @DisplayName("GET /v1/metadata/version")
    class GetVersion {

        @Test
        void unauthenticated_getResult() throws Exception {
            when(buildInfoProperties.version()).thenReturn("1.0.0");
            when(buildInfoProperties.gitHash()).thenReturn("1234567");
            when(buildInfoProperties.branchName()).thenReturn("main");

            api
                .perform(get("/v1/metadata/version"))
                .andExpectAll(
                    status().isOk(),
                    content().contentType(MediaType.APPLICATION_JSON),
                    jsonPath("$.version").isString(),
                    jsonPath("$.gitHash").isString(),
                    jsonPath("$.branchName").isString()
                );
        }
    }
}
