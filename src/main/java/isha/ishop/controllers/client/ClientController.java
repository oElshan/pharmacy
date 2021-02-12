package isha.ishop.controllers.client;

import isha.ishop.dto.OrderForm;
import isha.ishop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletContext;

@Controller
public class ClientController {

    @Autowired
    private ProductService productService;

    @Autowired
    ServletContext servletContext;


    @RequestMapping(value = "/checkout" ,method = RequestMethod.GET)
    public  String createOrder(  Model model) {
        model.addAttribute("orderForm", new OrderForm());
        model.addAttribute("breadcrumb", "Checkout Process");
        return "checkout";
    }

    //    Реализуй тут можно добавлять в метод аргументы и спринг тебе их поставит уже готовые , но тольок те которые сам знает
    //    если у него нет такиих обьектов допустим ты написала какой то класс , надо обявить его бином и спринг будет знать о нем
    //    и зауснет тебе сюда этот обьект если ты укажешь в этом методое его, допустим я укзала Model

    /** @see @link https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-arguments*/
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String showCart(Model model) {
        model.addAttribute("breadcrumb", "Checkout Process");
//        теперь в cart можно типлифом найти  перменную x1 и распечатать в нудный тег
        return "cart";
    }


}
