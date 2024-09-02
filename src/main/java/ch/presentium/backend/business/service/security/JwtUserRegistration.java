package ch.presentium.backend.business.service.security;

import ch.presentium.backend.api.security.mapper.UserMapper;
import ch.presentium.backend.business.model.user.Person;
import ch.presentium.backend.business.model.user.Student;
import ch.presentium.backend.business.model.user.Teacher;
import ch.presentium.backend.business.model.user.User;
import ch.presentium.backend.business.repository.PersonRepository;
import ch.presentium.backend.business.repository.UserRepository;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JwtUserRegistration {

    private final UserRepository userRepository;
    private final PersonRepository personRepository;
    private final UserMapper userMapper;

    @Transactional(rollbackFor = Throwable.class)
    public User registerUser(Jwt jwt) {
        var user = userRepository.findById(jwt.getSubject()).orElseGet(User::new);
        userMapper.updateUser(jwt, user);

        if (Objects.isNull(user.getPerson())) {
            var person = personRepository
                .findByFullName(user.getDisplayName())
                .orElseGet(() -> createPerson(jwt).setFullName(user.getDisplayName()));

            user.setPerson(person);
        }

        return userRepository.save(user);
    }

    private Person createPerson(Jwt jwt) {
        List<String> roles = jwt.getClaimAsStringList("roles");
        if (roles.contains("teacher") || roles.contains("admin")) {
            return new Teacher();
        } else {
            return new Student();
        }
    }
}
