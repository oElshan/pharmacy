package isha.ishop.controllers.ajax;

import isha.ishop.entity.Product;
import isha.ishop.form.SearchItemsForm;
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

    @RequestMapping( value = "/ajax/json/searchItems",method = RequestMethod.POST, produces = "application/json")
    public ModelAndView shopingCartStatus(@RequestBody SearchItemsForm search, ModelMap modelMap) {

        System.out.println(search);

        List<Product> products = productService.findProductByNameLike(search.getSearchName());
        modelMap.addAttribute("products", products);
        return new ModelAndView("fragment/dataTableItems :: dataTableItems", modelMap);
    }
}
