package ch.presentium.backend.business.service.security;

import ch.presentium.backend.api.security.mapper.UserMapper;
import ch.presentium.backend.business.model.user.User;
import ch.presentium.backend.business.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class JwtUserRegistration {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional(rollbackFor = Throwable.class)
    public User registerUser(Jwt jwt) {
        var user = userRepository.findById(jwt.getSubject()).orElseGet(User::new);
        userMapper.updateUser(jwt, user);
        return userRepository.save(user);
    }
}
