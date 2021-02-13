package isha.store.controllers.client;

import isha.store.entity.Product;
import isha.store.entity.SpecCategory;
import isha.store.services.ProductService;
import isha.store.utils.Constants;
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
    ServletContext servletContext;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String welcome(Model model,HttpSession session){
//        List<Product> products = productService.listAllProducts(1, Constants.MAX_PRODUCTS_PER_HTML_PAGE);
        List<SpecCategory> specCategories= (List<SpecCategory>) servletContext.getAttribute(Constants.SPECCATEGORY_LIST);
        List<Product> products = productService.listAllProductsForSpecCategory(specCategories.get(0).getId(),1, Constants.MAX_PRODUCTS_PER_HTML_PAGE);
        for (Product product : products) {
            System.out.println(product);
        }
        model.addAttribute("products", products);
        model.addAttribute("specCategory", specCategories.get(0));
        model.addAttribute("specCategoryList", specCategories);
/**        1. Найти в сессии корзину
 *         2. если ее там нету найти ее в куки
 *              2.1 найти атрибут корзина = сериализация корзины
 *              2.2 востановить корзину
 *         3.если нету завести новую каррзину
 *         4.сохранить корзину в сессию
 *         5.сохранить данные карзины в куки
 *
 */
        return "index";
    }

}
