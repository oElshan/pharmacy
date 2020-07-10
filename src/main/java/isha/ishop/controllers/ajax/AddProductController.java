package isha.ishop.controllers.ajax;

import com.fasterxml.jackson.annotation.JsonView;
import isha.ishop.entity.Product;
import isha.ishop.model.ShoppingCart;
import isha.ishop.model.ShoppingCartItem;
import isha.ishop.services.ProductService;
import isha.ishop.utils.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class AddProductController {

    @Autowired
    ProductService productService;


    @JsonView(Views.Public.class)
    @GetMapping("/ajax/json/product/add")
    public String addShopingCart(@RequestParam("idProduct") long idProduct, HttpSession session) {

        System.out.println("запрос принят "+idProduct);
        Product product =  productService.findProductById(idProduct);
        System.out.println(product.toString());

        ShoppingCart shoppingCart =(ShoppingCart) session.getAttribute("CURRENT_SHOPPING_CART");
        shoppingCart.addProduct(product,1);
        session.setAttribute("CURRENT_SHOPPING_CART", shoppingCart);

        return "fragment/shopingCart :: shopingCart";
    }
}
