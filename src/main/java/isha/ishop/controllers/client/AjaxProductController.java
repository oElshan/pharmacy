package isha.ishop.controllers.client;

import isha.ishop.entity.Product;
import isha.ishop.entity.SpecCategory;
import isha.ishop.services.ClientService;
import isha.ishop.services.ProductService;
import isha.ishop.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.util.List;

@Controller

@RequestMapping(value = "/ajax/products")
public class AjaxProductController {

    @Autowired
    ClientService clientService;
    @Autowired
    ProductService productService;

    @Autowired
    ServletContext servletContext;


    @GetMapping("/search")
    public ModelAndView searchProductByNameLike(@RequestParam("name") String name, ModelMap modelMap) {
        List<Product> products = productService.findByNameContaining(name);
        modelMap.addAttribute("products", products);
        return new ModelAndView("fragment/data-table-items :: data-table-items", modelMap);
    }

//    @RequestMapping( value = "/filter-price",method = RequestMethod.GET)
//    public String filterProductByPrice(@RequestParam BigDecimal min,@RequestParam BigDecimal max,@RequestParam Integer idCategory, ModelMap modelMap) {
//        System.out.println(min+"--"+max);
////        modelMap.addAttribute("producerList",servletContext.getAttribute(Constants.PRODUCER_LIST));
//        return "redirect:/";
//    }

    @GetMapping
    public String getProductsListForSpecCategory(@RequestParam("idCatalog") int idCatalog, Model model) {
        List<SpecCategory> specCategories = (List<SpecCategory>) servletContext.getAttribute(Constants.SPECCATEGORY_LIST);
        model.addAttribute("specCategory", specCategories.get(idCatalog-1));
        model.addAttribute("products", productService.listAllProductsForSpecCategory(idCatalog, 1, Constants.MAX_PRODUCTS_PER_HTML_PAGE));
        return "fragment/products-list-home :: products";
    }


}
