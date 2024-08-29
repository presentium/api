package ch.presentium.backend.api.device;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import ch.presentium.backend.api.AbstractControllerTest;
import ch.presentium.backend.business.model.PresenceBox;
import ch.presentium.backend.business.model.schedule.ClassSession;
import ch.presentium.backend.business.model.schedule.SchoolClass;
import ch.presentium.backend.business.model.user.Student;
import ch.presentium.backend.business.model.user.Teacher;
import ch.presentium.backend.business.model.user.User;
import ch.presentium.backend.business.repository.PresenceBoxRepository;
import ch.presentium.backend.business.repository.SchoolClassRepository;
import ch.presentium.backend.business.repository.StudentRepository;
import ch.presentium.backend.business.repository.TeacherRepository;
import ch.presentium.backend.business.repository.UserRepository;
import ch.presentium.backend.business.service.ClassSessionService;
import ch.presentium.backend.rpc.DeviceBridgeService;
import ch.presentium.backend.rpc.DeviceMode;
import ch.presentium.backend.rpc.EnrollStudentEvent;
import ch.presentium.backend.rpc.PresenceCheckEvent;
import ch.presentium.backend.security.WithMockAdminUser;
import ch.presentium.backend.security.WithMockStudentUser;
import ch.presentium.backend.security.WithMockTeacherUser;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;

@WebMvcTest(DeviceController.class)
class DeviceControllerTest extends AbstractControllerTest {

    @MockBean
    private PresenceBoxRepository deviceRepository;

    @MockBean
    private UserRepository userRepository;

    @MockBean
    private StudentRepository studentRepository;

    @MockBean
    private TeacherRepository teacherRepository;

    @MockBean
    private SchoolClassRepository schoolClassRepository;

    @MockBean
    private DeviceBridgeService deviceBridgeService;

    @MockBean
    private ClassSessionService classSessionService;

    @Nested
    @DisplayName("GET /v1/devices?assigned=@me")
    class GetAssignedDevices {

        @Test
        @WithMockStudentUser
        void asStudent_forbidden() throws Exception {
            api.perform(get("/v1/devices?assigned=@me")).andExpect(status().isForbidden());
        }

        @Test
        @WithMockTeacherUser
        void asTeacher_canListAssignedDevices() throws Exception {
            when(mappingContext.isDeviceConnected(any())).thenReturn(true);
            when(mappingContext.getDeviceMode(any())).thenReturn(DeviceMode.IDLE);
            when(userRepository.findById(anyString())).thenReturn(
                Optional.of(
                    new User()
                        .setPerson(
                            new Teacher()
                                .setDevices(Set.of(new PresenceBox().setId(UUID.randomUUID()).setName("device1")))
                        )
                )
            );

            api
                .perform(get("/v1/devices?assigned=@me"))
                .andExpectAll(
                    status().isOk(),
                    content().contentType(MediaType.APPLICATION_JSON),
                    jsonPath("$").isArray(),
                    jsonPath("$[0].id").isString(),
                    jsonPath("$[0].commonName").value("device1"),
                    jsonPath("$[0].connected").value(true),
                    jsonPath("$[0].mode").value(DeviceMode.IDLE.name())
                );
        }
    }

    @Nested
    @DisplayName("GET /v1/devices")
    class GetDevices {

        @Test
        @WithMockStudentUser
        void asStudent_forbidden() throws Exception {
            api.perform(get("/v1/devices")).andExpect(status().isForbidden());
        }

        @Test
        @WithMockTeacherUser
        void asTeacher_forbidden() throws Exception {
            api.perform(get("/v1/devices")).andExpect(status().isForbidden());
        }

        @Test
        @WithMockAdminUser
        void asAdmin_canListDevices() throws Exception {
            when(deviceRepository.findAll()).thenReturn(
                List.of(
                    new PresenceBox().setId(UUID.randomUUID()).setName("device1"),
                    new PresenceBox().setId(UUID.randomUUID()).setName("device2")
                )
            );

            api
                .perform(get("/v1/devices"))
                .andExpectAll(
                    status().isOk(),
                    content().contentType(MediaType.APPLICATION_JSON),
                    jsonPath("$").isArray(),
                    jsonPath("$[0].id").isString(),
                    jsonPath("$[0].commonName").value("device1"),
                    jsonPath("$[1].id").isString(),
                    jsonPath("$[1].commonName").value("device2")
                );
        }
    }

    @Nested
    @DisplayName("GET /v1/devices/{deviceId}")
    class GetDevice {

        @Test
        @WithMockStudentUser
        void asStudent_forbidden() throws Exception {
            api.perform(get("/v1/devices/{deviceId}", UUID.randomUUID())).andExpect(status().isForbidden());
        }

        @Test
        @WithMockTeacherUser
        void asTeacher_canGetDevice() throws Exception {
            UUID deviceId = UUID.randomUUID();
            when(deviceRepository.findById(deviceId)).thenReturn(
                Optional.of(new PresenceBox().setId(deviceId).setName("device1"))
            );

            api
                .perform(get("/v1/devices/{deviceId}", deviceId))
                .andExpectAll(
                    status().isOk(),
                    content().contentType(MediaType.APPLICATION_JSON),
                    jsonPath("$.id").isString(),
                    jsonPath("$.commonName").value("device1")
                );
        }

        @Test
        @WithMockTeacherUser
        void unknownDevice_notFound() throws Exception {
            api.perform(get("/v1/devices/{deviceId}", UUID.randomUUID())).andExpect(status().isNotFound());
        }
    }

    @Nested
    @DisplayName("POST /v1/devices/{deviceId}/actions/assign")
    class AssignDevice {

        @Test
        @WithMockStudentUser
        void asStudent_forbidden() throws Exception {
            api
                .perform(
                    post("/v1/devices/{deviceId}/actions/assign", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"teacher\": \"%s\" }".formatted(UUID.randomUUID()))
                )
                .andExpect(status().isForbidden());
        }

        @Test
        @WithMockTeacherUser
        void asTeacher_forbidden() throws Exception {
            api
                .perform(
                    post("/v1/devices/{deviceId}/actions/assign", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"teacher\": \"%s\" }".formatted(UUID.randomUUID()))
                )
                .andExpect(status().isForbidden());
        }

        @Test
        @WithMockAdminUser
        void asAdmin_malformedBody_badRequest() throws Exception {
            api
                .perform(
                    post("/v1/devices/{deviceId}/actions/assign", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}")
                )
                .andExpect(status().isBadRequest());
        }

        @Test
        @WithMockAdminUser
        void asAdmin_canAssignDevice() throws Exception {
            var deviceId = UUID.randomUUID();
            var device = new PresenceBox().setId(deviceId).setName("device1");

            var teacherId = UUID.randomUUID();
            var teacher = (Teacher) new Teacher().setDevices(new HashSet<>()).setId(teacherId);
            when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(device));
            when(teacherRepository.findById(teacherId)).thenReturn(Optional.of(teacher));

            api
                .perform(
                    post("/v1/devices/{deviceId}/actions/assign", deviceId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"teacher\": \"%s\" }".formatted(teacherId))
                )
                .andExpect(status().isOk());

            assertSame(teacher, device.getTeacher());
        }
    }

    @Nested
    @DisplayName("POST /v1/devices/{deviceId}/actions/unassign")
    class UnassignDevice {

        @Test
        @WithMockStudentUser
        void asStudent_forbidden() throws Exception {
            api
                .perform(post("/v1/devices/{deviceId}/actions/unassign", UUID.randomUUID()))
                .andExpect(status().isForbidden());
        }

        @Test
        @WithMockTeacherUser
        void asTeacher_forbidden() throws Exception {
            api
                .perform(post("/v1/devices/{deviceId}/actions/unassign", UUID.randomUUID()))
                .andExpect(status().isForbidden());
        }

        @Test
        @WithMockAdminUser
        void asAdmin_canUnassignDevice() throws Exception {
            var deviceId = UUID.randomUUID();
            var device = new PresenceBox()
                .setId(deviceId)
                .setName("device1")
                .setTeacher(new Teacher().setDevices(new HashSet<>()));
            when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(device));

            api.perform(post("/v1/devices/{deviceId}/actions/unassign", deviceId)).andExpect(status().isOk());

            assertNull(device.getTeacher());
        }
    }

    @Nested
    @DisplayName("POST /v1/devices/{deviceId}/actions/enroll-student")
    class EnrollStudent {

        @Test
        @WithMockStudentUser
        void asStudent_forbidden() throws Exception {
            api
                .perform(
                    post("/v1/devices/{deviceId}/actions/enroll-student", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"student\": \"%s\" }".formatted(UUID.randomUUID()))
                )
                .andExpect(status().isForbidden());
        }

        @Test
        @WithMockTeacherUser
        void asTeacher_canStartEnrollment() throws Exception {
            var deviceId = UUID.randomUUID();
            var studentId = UUID.randomUUID();
            var device = new PresenceBox().setId(deviceId).setName("device1");
            when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(device));

            var student = (Student) new Student().setId(studentId);
            when(studentRepository.findById(studentId)).thenReturn(Optional.of(student));

            api
                .perform(
                    post("/v1/devices/{deviceId}/actions/enroll-student", deviceId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"student\": \"%s\" }".formatted(studentId))
                )
                .andExpect(status().isAccepted());

            verify(deviceBridgeService, times(1)).send(anyString(), any(EnrollStudentEvent.class));
        }
    }

    @Nested
    @DisplayName("POST /v1/devices/{deviceId}/actions/presence-check")
    class PresenceCheck {

        @Test
        @WithMockStudentUser
        void asStudent_forbidden() throws Exception {
            api
                .perform(
                    post("/v1/devices/{deviceId}/actions/presence-check", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"schoolClass\": 1 }")
                )
                .andExpect(status().isForbidden());
        }

        @Test
        @WithMockTeacherUser
        void asTeacher_canStartPresenceCheck() throws Exception {
            var device = new PresenceBox().setId(UUID.randomUUID()).setName("device1");
            when(deviceRepository.findById(any())).thenReturn(Optional.of(device));

            var schoolClass = new SchoolClass().setId(1L).setName("class1");
            when(schoolClassRepository.findById(any())).thenReturn(Optional.of(schoolClass));

            when(classSessionService.createSession(any())).thenReturn(new ClassSession().setId(UUID.randomUUID()));

            api
                .perform(
                    post("/v1/devices/{deviceId}/actions/presence-check", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"schoolClass\": 1 }")
                )
                .andExpect(status().isAccepted());

            verify(deviceBridgeService, times(1)).send(anyString(), any(PresenceCheckEvent.class));
        }
    }
}
