package isha.ishop.controllers.ajax;

import com.fasterxml.jackson.annotation.JsonView;
import isha.ishop.entity.Product;
import isha.ishop.model.ShoppingCart;
import isha.ishop.model.ShoppingCartItem;
import isha.ishop.services.ProductService;
import isha.ishop.utils.Views;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
public class AddProductController {

    ProductService productService;


    @JsonView(Views.Public.class)
    @GetMapping("/ajax/json/product/add/{id}")
    @ResponseBody
    public ShoppingCart addShopingCart(@PathVariable long id, HttpSession session) {

        Product product =  productService.findProductById(id);

        ShoppingCart shoppingCart =(ShoppingCart) session.getAttribute("CURRENT_SHOPPING_CART");
        shoppingCart.addProduct(product,1);
        session.setAttribute("CURRENT_SHOPPING_CART", shoppingCart);

        System.out.println(shoppingCart.toString());
        return shoppingCart;
    }
}
