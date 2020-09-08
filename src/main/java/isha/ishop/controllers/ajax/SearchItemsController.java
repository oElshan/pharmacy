package isha.ishop.controllers.ajax;

import isha.ishop.entity.Product;
import isha.ishop.form.SearchForm;
import isha.ishop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class SearchItemsController {

    @Autowired
    ProductService productService;

    @RequestMapping( value = "/ajax/json/search-items",method = RequestMethod.POST, produces = "application/json")
    public ModelAndView shopingCartStatus(@RequestBody SearchForm search, ModelMap modelMap) {

        System.out.println(search);

//        List<Product> products = productService.findProductByNameLike(search.getSearchName());
        List<Product> products = productService.findByNameContaining(search.getSearchName());
        modelMap.addAttribute("products", products);
        return new ModelAndView("fragment/dataTableItems :: dataTableItems", modelMap);
    }
}
