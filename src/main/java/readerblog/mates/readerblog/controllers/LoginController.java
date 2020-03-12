package readerblog.mates.readerblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {
    @GetMapping("/signin")
    public String showMyLoginPage() {
        return "login";
    }




    @GetMapping("/signup")
    public String showMySignUpPage() {
        return "registration-form";}
}



