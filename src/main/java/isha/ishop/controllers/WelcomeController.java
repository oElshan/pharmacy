package isha.ishop.controllers;

import isha.ishop.entity.Producer;
import isha.ishop.model.ShoppingCart;
import isha.ishop.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class WelcomeController {
    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(){


        System.out.println("producers - "+servletContext.getAttribute(Constants.PRODUCER_LIST));


/**        1. Найти в сессии корзину
 *         2. если ее там нету найти ее в куки
 *              2.1 найти атрибут корзина = сериализация корзины
 *              2.2 востановить корзину
 *         3.если нету завести новую каррзину
 *         4.сохранить корзину в сессию
 *         5.сохранить данные карзины в куки
 *
 */
        return "home";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "login-and-register";
    }


}
