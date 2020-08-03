package isha.ishop.controllers;

import isha.ishop.entity.Product;
import isha.ishop.form.OrderForm;
import isha.ishop.services.ProductService;
import isha.ishop.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class WelcomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private ServletContext servletContext;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Model model,HttpSession session){
        List<Product> products = productService.listAllProducts(1, Constants.MAX_PRODUCTS_PER_HTML_PAGE);
        model.addAttribute("products", products);

//        убери этот кусок
//         ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(Constants.CURRENT_SHOPPING_CART);
//
//        for (Product product : products) {
//            shoppingCart.addProduct(product,1);
//        }
//        session.setAttribute(Constants.CURRENT_SHOPPING_CART, shoppingCart);

//
/**        1. Найти в сессии корзину
 *         2. если ее там нету найти ее в куки
 *              2.1 найти атрибут корзина = сериализация корзины
 *              2.2 востановить корзину
 *         3.если нету завести новую каррзину
 *         4.сохранить корзину в сессию
 *         5.сохранить данные карзины в куки
 *
 */
        return "index-2";
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String adminPage(Model model,HttpSession session) {

        return "admin";
    }

    @RequestMapping(value = "/admin/orders", method = RequestMethod.GET)
    public String showOrders(Model model,HttpSession session) {

        return "orders";
    }

    @RequestMapping(value = "/admin/items", method = RequestMethod.GET)
    public String showItems(Model model,HttpSession session) {

        return "items";
    }

    @RequestMapping(value = "/checkout" ,method = RequestMethod.GET)
    public  String createOrder(  Model model) {
        model.addAttribute("orderForm", new OrderForm());


        return "checkout";
    }
}
