package isha.ishop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ClientController {

    @RequestMapping(value = "/checkout" ,method = RequestMethod.GET)
    public  String createOrder() {

        return "checkout";
    }
}
