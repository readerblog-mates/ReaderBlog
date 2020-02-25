package readerblog.mates.readerblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import readerblog.mates.readerblog.entities.User;
import readerblog.mates.readerblog.enums.AuthProvider;
import readerblog.mates.readerblog.exception.BadRequestException;

import readerblog.mates.readerblog.payload.AuthResponse;
import readerblog.mates.readerblog.payload.LoginRequest;
import readerblog.mates.readerblog.payload.SignUpRequest;
import readerblog.mates.readerblog.repositories.UserRepository;
import readerblog.mates.readerblog.security.TokenProvider;

import javax.validation.Valid;

@Controller
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenProvider tokenProvider;

    @PostMapping("/login")
    @ResponseBody
    public String authenticateUser(@Valid LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = tokenProvider.createToken(authentication);
        ResponseEntity.ok(new AuthResponse(token));
        return "/";
    }
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String registerUser(@Valid SignUpRequest signUpRequest, Model model ) {
        if(userRepository.existsByEmail(signUpRequest.getEmail())) {
            throw new BadRequestException("Email address already in use.");
        }

        // Creating user's account
        User user = new User();
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setNickName(signUpRequest.getNickName());
        user.setEmail(signUpRequest.getEmail());
        user.setPassword(signUpRequest.getPassword());
        user.setProvider(AuthProvider.LOCAL);

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        User result = userRepository.save(user);
        model.addAttribute("msg", result.getFirstName());
//        Если нужно вернуть ResponseEntity
//        URI location = ServletUriComponentsBuilder
//                .fromCurrentContextPath().path("/user/me")
//                .buildAndExpand(result.getId()).toUri();
//
//        return ResponseEntity.created(location)
//                .body(new ApiResponse(true, "User registered successfully@"));

        return "index";

    }

}
