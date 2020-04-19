package isha.ishop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class WelcomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome() {
        return "home1";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login-and-register";
    }
}
