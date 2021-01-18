package isha.ishop.controllers;

import isha.ishop.entity.Category;
import isha.ishop.entity.Producer;
import isha.ishop.entity.Product;
import isha.ishop.entity.Subcategory;
import isha.ishop.services.ProductService;
import isha.ishop.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class ProductController {

    @Autowired
    ProductService productService;
    @Autowired
    ServletContext servletContext;
    // TODO: 2020-11-30 вопрос по реквесту метода get каким образом запрашивать категории праметром или url
    @RequestMapping(value = "/category/*/*/{idCategory}" ,method = RequestMethod.GET)
    public  String showProductBySubcategory(@PathVariable long idCategory , @RequestParam("page") Optional<Integer> page, Model model, HttpServletRequest request) {

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(idCategory);
        System.out.println(request.getRequestURI());


        int currentPage = page.orElse(1);
        Page<Product> productsPage = productService.findAllProductBySubCategoryId(idCategory, currentPage, 12);
        List<Producer> producers = productService.getProducersBySubCategory(idCategory);

        model.addAttribute("products", productsPage.getContent());
        model.addAttribute("producers", producers);
        model.addAttribute("category", productService.findSubcategoryById(idCategory));
        model.addAttribute("breadcrumb", productService.findSubcategoryById(idCategory).getName());
        model.addAttribute("productsPage", productsPage);
        model.addAttribute("urlPagination", request.getRequestURI()+"?");
        model.addAttribute("pageNumbers", pagination(productsPage));
        model.addAttribute("minMax", productService.getMinMaxPriceProductByCategory(idCategory));
        return "product-grid";
    }


    @RequestMapping(value = "/category/*/{idCategory}", method = RequestMethod.GET)
    public String showProductByCategory(@PathVariable int idCategory, @RequestParam("page") Optional<Integer> page, Model model,HttpServletRequest request) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        System.out.println(idCategory);
        System.out.println(request.getRequestURI());
        Category category = productService.findCategoryById(idCategory);
        List<Subcategory> subcategories = (List<Subcategory>) servletContext.getAttribute(Constants.SUBCATEGORY_LIST);
        model.addAttribute("subcategories", subcategories.stream().filter(s -> s.getCategory().getId() == idCategory).collect(Collectors.toList()));
        model.addAttribute("category", category);
        model.addAttribute("breadcrumb", category.getName());

        return "category-grid";
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

    @GetMapping(value = "/search")
    public String searchItemGrid(@RequestParam("search") String search,@RequestParam("page") Optional<Integer> page, Model model,HttpServletRequest request) {
        int currentPage = page.orElse(1);
        Page<Product> productsPage = productService.findProductByNameLike(search,currentPage,12);
        List<Product> products = productsPage.getContent();
        model.addAttribute("pageNumbers", pagination(productsPage));
        model.addAttribute("products", products);
        model.addAttribute("productsPage", productsPage);
        model.addAttribute("breadcrumb", search);
//        model.addAttribute("url", "/search/?search="+search+"&");
        model.addAttribute("minMax", productService.getMinMaxPriceProductBySearchName(search));
        model.addAttribute("urlPagination", request.getRequestURI()+"?search="+search+"&");
        model.addAttribute("producers", productService.getProducersBySearchProduct(search));

        return "product-grid";
    }

    public List pagination(Page pages) {
        int totalPages = pages.getTotalPages();
        List<Integer> pageNumbers = new ArrayList<>();
        if (totalPages > 0) {
           pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
        }
        return pageNumbers;
    }


}
