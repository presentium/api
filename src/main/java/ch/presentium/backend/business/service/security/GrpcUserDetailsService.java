package ch.presentium.backend.business.service.security;

import ch.presentium.backend.business.model.PresenceBox;
import ch.presentium.backend.business.repository.PresenceBoxRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GrpcUserDetailsService implements UserDetailsService {

    private final PresenceBoxRepository presenceBoxRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return presenceBoxRepository.save(
            presenceBoxRepository.findByName(username).orElseGet(PresenceBox::new).setName(username)
        );
    }
}
