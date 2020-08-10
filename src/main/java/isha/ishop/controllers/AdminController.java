package isha.ishop.controllers;

import isha.ishop.entity.Product;
import isha.ishop.form.EditProductForm;
import isha.ishop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;

@Controller
public class AdminController {
    @Autowired
    ProductService productService;



   @GetMapping(value = "/admin/single-product")
    public String editItem( Model model) {
        model.addAttribute("loginError", true);
        return "/single-product";
    }

    @GetMapping(value = "/admin/editItem{id}")
    public String viewEditItem(@RequestParam("id") Long id, Model model) {
        Product product = productService.findProductById(id);
        model.addAttribute("editProduct", product);
        model.addAttribute("editProductForm", new EditProductForm());
        model.addAttribute("specCategoryList",productService.listAllSpecCategory());
        System.out.println("Product id"+id);
        return "edit-items";
    }

    @RequestMapping(value = "/admin/editItem", method = RequestMethod.POST)
    public String editItem(@Valid @ModelAttribute("editProductForm") EditProductForm editProductForm, BindingResult bindingResult, Model model) throws IOException {

       if (bindingResult.hasErrors()){
           System.out.println(editProductForm);
           Product product = productService.findProductById(editProductForm.getId());
           model.addAttribute("editProduct", product);
//           model.addAttribute("editProductForm", new EditProductForm());
           model.addAttribute("specCategoryList",productService.listAllSpecCategory());
           return "edit-items";
       }
       Product product =  productService.editProduct(editProductForm);

        System.out.println(product);

        return "admin";
    }

}
