package isha.ishop.controllers;

import isha.ishop.entity.Product;
import isha.ishop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

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

    @GetMapping(value = "/search{search}")
    public String searchItemGrid(@RequestParam("search") String search, Model model) {

        List<Product> products = productService.findProductByNameLike(search);
        model.addAttribute("products", products);
        return "category-grid";
    }




}
