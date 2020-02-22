package readerblog.mates.readerblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import readerblog.mates.readerblog.entities.User;
import readerblog.mates.readerblog.exception.ResourceNotFoundException;
import readerblog.mates.readerblog.repositories.UserRepository;
import readerblog.mates.readerblog.security.CurrentUser;
import readerblog.mates.readerblog.security.UserPrincipal;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/me")
    @PreAuthorize("hasRole('USER')")
    public User getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userRepository.findById(userPrincipal.getId())
                .orElseThrow(() -> new ResourceNotFoundException("User", "id", userPrincipal.getId()));
    }
}
