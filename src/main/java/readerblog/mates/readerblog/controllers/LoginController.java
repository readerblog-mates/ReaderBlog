package readerblog.mates.readerblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String showMyLoginPage() {
        return "login";
    }


    @GetMapping("/signup")
    public String showMySignUpPage() {
        return "registration-form";}
}



