package isha.ishop.controllers.ajax;

import com.fasterxml.jackson.annotation.JsonView;
import isha.ishop.model.ShoppingCart;
import isha.ishop.utils.Constants;
import isha.ishop.utils.Views;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class ShopingCartController {


    @JsonView(Views.Public.class)
    @GetMapping(value = "/ajax/json/shoppingCart",produces ="application/json" )
    @ResponseBody
    public ShoppingCart shopingCartStatus(HttpSession session) {

        ShoppingCart shoppingCart =(ShoppingCart) session.getAttribute(Constants.CURRENT_SHOPPING_CART);

        return shoppingCart;
    }

    @GetMapping("/ajax/deleteItem")
    public String deleteIetemFromShopingCart(@RequestParam("idProduct") long idProduct, HttpSession session) {
       ShoppingCart shoppingCart =(ShoppingCart) session.getAttribute(Constants.CURRENT_SHOPPING_CART);
       shoppingCart.removeProduct(idProduct,1);
       session.setAttribute(Constants.CURRENT_SHOPPING_CART,shoppingCart);

        return "fragment/shopingCart :: shopingCart";
    }

    @GetMapping("/ajax/deleteItemFromShoppingCart")
    public String deleteItemFromShoppingCartView(HttpSession session) {

        return "fragment/viewShoppingCart :: viewSoppingCart";
    }

}
