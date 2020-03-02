package readerblog.mates.readerblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import readerblog.mates.readerblog.security.UserPrincipal;
import org.springframework.security.core.Authentication;
import java.security.Principal;

@Controller
public class MainController {
    @GetMapping("/")
    public String index(){
      return "index";
    }
}
