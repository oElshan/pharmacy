package isha.ishop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ProductController {

    @RequestMapping(value = "/subcategory/{value}" ,method = RequestMethod.GET)
    public  String showProducrBySubcategory(@PathVariable String value) {

        return "category-grid";
    }

    //    Реализуй тут можно добавлять в метод аргументы и спринг тебе их поставит уже готовые , но тольок те которые сам знает
//    если у него нет такиих обьектов допустим ты написала какой то класс , надо обявить его бином и спринг будет знать о нем
//    и зауснет тебе сюда этот обьект если ты укажешь в этом методое его, допустим я укзала Model
    /** @see @link https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-ann-arguments*/
    @RequestMapping(value = "/cart", method = RequestMethod.GET)
    public String showCart(Model model) {
        model.addAttribute("x1", "'это x1 ");
//        теперь в cart можно типлифом найти  перменную x1 и распечатать в нудный тег
        return "cart";
    }


}
