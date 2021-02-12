package isha.ishop.controllers.client;

import isha.ishop.controllers.AbstractProductController;
import isha.ishop.dto.FilterProduct;
import isha.ishop.entity.Category;
import isha.ishop.entity.Product;
import isha.ishop.entity.Subcategory;
import isha.ishop.services.ProductService;
import isha.ishop.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = "/categories")
public class CategoryController extends AbstractProductController {

    @Autowired
    ProductService productService;

    @Autowired
    ServletContext servletContext;



    // TODO: 2020-11-30 вопрос по реквесту метода get каким образом запрашивать категории праметром или url
    @RequestMapping(value = "/*/*",method = RequestMethod.POST)
    public  String showProductByFilter(@ModelAttribute("searchProductForm") FilterProduct filterProduct, Model model, HttpServletRequest request, RedirectAttributes redirectAttributes) {

        redirectAttributes.addFlashAttribute("filterProduct", filterProduct);
        return "redirect:"+request.getRequestURI();
    }

    @RequestMapping(value = "/*/{subCategory}" ,method = RequestMethod.GET)
  public  String showProductBySubcategory(@PathVariable("subCategory") String subCategory , @RequestParam("page") Optional<Integer> page, Model model, HttpServletRequest request) {

        int currentPage =page.orElse(1);
        Page<Product> productsPage;

        if (model.containsAttribute("filterProduct")) {
            FilterProduct filterProduct = (FilterProduct) model.getAttribute("filterProduct");
            productsPage = productService.getProductByFilter(filterProduct,currentPage,Constants.MAX_PRODUCTS_PER_HTML_PAGE);
            model.addAttribute("filterProduct",  filterProduct);
            model.addAttribute("urlPagination", request.getRequestURI());
            model.addAttribute("minMax", productService.getMinMaxPriceProductByCategoryURL(subCategory));
            model.addAttribute("filterPrice", filterProduct.getPrice());
            model.addAttribute("filterProducersIdSet", filterProduct.getProducers());
            model.addAttribute("isFilterPrice", true);
        } else {
            productsPage =productService.findAllProductByCategoryURL(subCategory, currentPage, Constants.MAX_PRODUCTS_PER_HTML_PAGE);
            model.addAttribute("filterProduct", new FilterProduct());
            model.addAttribute("urlPagination", request.getRequestURI());
            model.addAttribute("minMax", productService.getMinMaxPriceProductByCategoryURL(subCategory));

        }

        Subcategory subcategory = productService.findSubcategoryByURL(subCategory);
        model.addAttribute("products", productsPage.getContent());
        model.addAttribute("producers", productService.getProducersByCategoryURL(subCategory));
        model.addAttribute("category",subcategory);
        model.addAttribute("breadcrumb", subcategory.getName());
        model.addAttribute("productsPage", productsPage);
        model.addAttribute("urlForm", request.getRequestURI());
        model.addAttribute("pageNumbers", pagination(productsPage));

        return "product-grid";
    }

    @GetMapping("/*")
    public String showCategories(Model model,HttpServletRequest request) {

        String categoryUrl = request.getRequestURI();
        Category category = productService.findCategoryByUrl(categoryUrl);
        List<Subcategory> subcategories = (List<Subcategory>) servletContext.getAttribute(Constants.SUBCATEGORY_LIST);
        model.addAttribute("subcategories", subcategories.stream().filter(s -> s.getCategory().getId() == category.getId()).collect(Collectors.toList()));
        model.addAttribute("category", category);
        model.addAttribute("breadcrumb", category.getName());

        return "category-grid";
    }

}
