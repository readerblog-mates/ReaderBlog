package readerblog.mates.readerblog.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import readerblog.mates.readerblog.entities.User;
import readerblog.mates.readerblog.exception.ResourceNotFoundException;
import readerblog.mates.readerblog.repositories.UserRepository;

/**
 * Created by rajeevkumarsingh on 02/08/17.
 */

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class CustomUserDetailsService implements UserDetailsService {

    final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email : " + email)
                );

        return UserPrincipal.create(user);
    }

    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }
}
