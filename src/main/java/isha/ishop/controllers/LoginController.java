package isha.ishop.controllers;

import isha.ishop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {

    @Autowired
    private ProductService productService;


//    @RequestMapping(value = "/login", method = RequestMethod.POST)
//    public String welcome( , HttpSession session, Model model){
//
//
//        List<Product> products = productService.listAllProducts(1, Constants.MAX_PRODUCTS_PER_HTML_PAGE);
//        model.addAttribute("products", products);
//
////        убери этот кусок
////         ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(Constants.CURRENT_SHOPPING_CART);
////
////        for (Product product : products) {
////            shoppingCart.addProduct(product,1);
////        }
////        session.setAttribute(Constants.CURRENT_SHOPPING_CART, shoppingCart);
//
////
///**        1. Найти в сессии корзину
// *         2. если ее там нету найти ее в куки
// *              2.1 найти атрибут корзина = сериализация корзины
// *              2.2 востановить корзину
// *         3.если нету завести новую каррзину
// *         4.сохранить корзину в сессию
// *         5.сохранить данные карзины в куки
// *
// */
//        return "index-2";
//    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login() {
        return "authentication";
    }

//    @RequestMapping(value = "/sign-in", method = RequestMethod.GET)
//    public String signIn(@AuthenticationPrincipal CurrentUser currentUser, HttpSession session, Model model) {
//        System.out.println("------------------------------"+currentUser);
//        session.setAttribute(Constants.CURRENT_ACCOUNT, currentUser);
//
//        return "redirect : /";
//    }

    // Login form with error
    @RequestMapping("/sign-in-failed")
    public String loginError(Model model) {
        model.addAttribute("loginError", true);
        return "authentication";
    }

}
