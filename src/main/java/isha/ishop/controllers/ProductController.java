package isha.ishop.controllers;

import isha.ishop.entity.Product;
import isha.ishop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @RequestMapping(value = "/subcategory/{value}" ,method = RequestMethod.GET)
    public  String showProducrBySubcategory(@PathVariable String subCategory,@RequestParam("page") Optional<Integer> page, Model model) {

        int currentPage = page.orElse(1);
        Page<Product> productsPage = productService.findAllProductBySubCategoryName(subCategory, currentPage, 12);
        model.addAttribute("products", productsPage.getContent());
        model.addAttribute("productsPage", productsPage);
        model.addAttribute("search", subCategory);
        int totalPages = productsPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

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

    @GetMapping(value = "/search")
    public String searchItemGrid(@RequestParam("search") String search,@RequestParam("page") Optional<Integer> page, Model model) {
        int currentPage = page.orElse(1);
        Page<Product> productsPage = productService.findProductByNameLike(search,currentPage,12);
        List<Product> products = productsPage.getContent();
        int totalPages = productsPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        model.addAttribute("products", products);
        model.addAttribute("productsPage", productsPage);
        model.addAttribute("search", search);

        return "category-grid";
    }




}
