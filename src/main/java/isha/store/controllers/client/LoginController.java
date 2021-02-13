package isha.store.controllers.client;

import isha.store.model.CurrentUser;
import isha.store.services.ProductService;
import isha.store.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {

    @Autowired
    private ProductService productService;


    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "authentication";
    }

    @RequestMapping(value = "/sign-in", method = RequestMethod.GET)
    public String signIn(@AuthenticationPrincipal CurrentUser currentUser, HttpSession session, Model model) {
        System.out.println("------------------------------"+currentUser);
        session.setAttribute(Constants.CURRENT_ACCOUNT, currentUser);

        return "redirect : /";
    }

    // Login form with error
    @RequestMapping("/sign-in-failed")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "authentication";
    }

}
