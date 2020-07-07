package isha.ishop.controllers.ajax;

import com.fasterxml.jackson.annotation.JsonView;
import isha.ishop.model.ShoppingCart;
import isha.ishop.utils.Constants;
import isha.ishop.utils.Views;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@Controller
public class ShopingCartController {

    @JsonView(Views.Public.class)
    @GetMapping(value = "/ajax/json/shopingCart",produces ="application/json" )
    @ResponseBody
    public ShoppingCart shopingCartStatus(HttpSession session) {

        ShoppingCart shoppingCart =(ShoppingCart) session.getAttribute(Constants.CURRENT_SHOPPING_CART);

        return shoppingCart;
    }

}
