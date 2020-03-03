package readerblog.mates.readerblog.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import readerblog.mates.readerblog.repository.UserRepository;
import readerblog.mates.readerblog.security.CurrentUser;
import readerblog.mates.readerblog.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import readerblog.mates.readerblog.services.UserService;

import java.security.Principal;

@Controller
public class MainController {
    @Autowired
    UserRepository userService;
    @GetMapping("/")
    public String index(){
      return "index";
    }
}
