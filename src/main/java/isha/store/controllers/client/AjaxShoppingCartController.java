package isha.store.controllers.client;

import com.fasterxml.jackson.annotation.JsonView;
import isha.store.entity.Product;
import isha.store.model.ShoppingCart;
import isha.store.services.ProductService;
import isha.store.utils.Constants;
import isha.store.utils.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class AjaxShoppingCartController {

    @Autowired
    ProductService productService;

    @JsonView(Views.Public.class)
    @GetMapping(value = "/ajax/json/shoppingCart",produces ="application/json" )
    @ResponseBody
    public ShoppingCart shoppingCartStatus(HttpSession session) {

        ShoppingCart shoppingCart =(ShoppingCart) session.getAttribute(Constants.CURRENT_SHOPPING_CART);

        return shoppingCart;
    }

    @DeleteMapping("/ajax/deleteItem")
    public String deleteItemFromShoppingCart(@RequestParam("idProduct") long idProduct, HttpSession session) {
       ShoppingCart shoppingCart =(ShoppingCart) session.getAttribute(Constants.CURRENT_SHOPPING_CART);
       shoppingCart.removeProduct(idProduct,1);
       session.setAttribute(Constants.CURRENT_SHOPPING_CART,shoppingCart);
        return "fragment/shopping-cart :: shopping-cart";
    }

    @GetMapping("/ajax/shopping-cart")
    public String deleteItemFromShoppingCartView(HttpSession session) {
        return "fragment/view-shopping-cart :: viewSoppingCart";
    }

    @GetMapping("/ajax/shopping-cart/add")
    public String addShoppingCart(@RequestParam("idProduct") long idProduct, HttpSession session) {
        System.out.println("запрос принят "+idProduct);
        Product product =  productService.findProductById(idProduct);
        System.out.println(product.toString());
        ShoppingCart shoppingCart =(ShoppingCart) session.getAttribute(Constants.CURRENT_SHOPPING_CART);
        shoppingCart.addProduct(product,1);
        session.setAttribute(Constants.CURRENT_SHOPPING_CART, shoppingCart);
        return "fragment/shopping-cart :: shopping-cart";
    }
}
