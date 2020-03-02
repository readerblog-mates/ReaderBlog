package readerblog.mates.readerblog.services;

import org.springframework.security.core.userdetails.UserDetailsService;
import readerblog.mates.readerblog.model.User;

public interface UserService  {
    User findByEmail(String email);
    Boolean existsByEmail(String email);
}
