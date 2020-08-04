package isha.ishop.controllers;

import isha.ishop.entity.Product;
import isha.ishop.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String editItem(@RequestParam("id") Long id, Model model) {
        Product product = productService.findProductById(id);
        model.addAttribute("editProduct", product);
        System.out.println("Product id"+id);
        return "edit-items";
    }
}
