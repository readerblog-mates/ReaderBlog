package readerblog.mates.readerblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import readerblog.mates.readerblog.utils.Utilities;

@Controller
public class MainController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainController.class);

    @GetMapping("/")
    public String index(){
        LOGGER.info("Invoke {} >", Utilities.getCurrentMethodName());
        return "index";
    }
    
}
