package readerblog.mates.readerblog.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import readerblog.mates.readerblog.model.Role;
import readerblog.mates.readerblog.model.User;
import readerblog.mates.readerblog.repository.UserRepository;

import java.util.Collection;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email).orElse(null);
    }


    @Override
    public Boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }


}


