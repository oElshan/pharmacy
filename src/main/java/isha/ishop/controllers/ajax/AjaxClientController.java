package isha.ishop.controllers.ajax;

import isha.ishop.entity.ClientOrder;
import isha.ishop.entity.Product;
import isha.ishop.form.OrderForm;
import isha.ishop.form.SearchForm;
import isha.ishop.model.ShoppingCart;
import isha.ishop.services.ClientService;
import isha.ishop.services.ProductService;
import isha.ishop.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class AjaxClientController {

    @Autowired
    ClientService clientService;
    @Autowired
    ProductService productService;


    @RequestMapping(value = "ajax/client/order", method = RequestMethod.POST, produces = "application/json")
    public String signIn(@Valid @RequestBody OrderForm orderForm, BindingResult bindingResult, Model model, HttpSession session) {


        if (bindingResult.hasErrors()) {
            return "fragment/checkout_page :: checkout";
        }
        System.out.println(orderForm);
        ShoppingCart shoppingCart = (ShoppingCart) session.getAttribute(Constants.CURRENT_SHOPPING_CART);
        ClientOrder clientOrder = clientService.newClientOrder(shoppingCart,orderForm);
        model.addAttribute("totalCost", shoppingCart.getTotalCost().doubleValue());

        shoppingCart.getItems().clear();
        shoppingCart.setTotalCost(BigDecimal.ZERO);
        shoppingCart.setTotalCount(0);
        session.setAttribute(Constants.CURRENT_SHOPPING_CART,shoppingCart);
        model.addAttribute("clientOrder", clientOrder);
        System.out.println(clientOrder);

        return "fragment/orderComplete :: orderComplete";
    }

    @RequestMapping( value = "/ajax/json/search-items",method = RequestMethod.POST, produces = "application/json")
    public ModelAndView shoppingCartStatus(@RequestBody SearchForm search, ModelMap modelMap) {

        System.out.println(search);

//        List<Product> products = productService.findProductByNameLike(search.getSearchName());
        List<Product> products = productService.findByNameContaining(search.getSearchName());
        modelMap.addAttribute("products", products);
        return new ModelAndView("fragment/dataTableItems :: dataTableItems", modelMap);
    }


    /**
     * Метод добовляет товар в корзину( ShoppingCart) и возвращает шаблон представления sopingCart.html
     *
     * @param idProduct
     * @param session
     * @return
     */

    @GetMapping("/ajax/json/product/add")
    public String addShoppingCart(@RequestParam("idProduct") long idProduct, HttpSession session) {

        System.out.println("запрос принят "+idProduct);
        Product product =  productService.findProductById(idProduct);
        System.out.println(product.toString());
/**  считываем с сессии обьект shoppingCart и кладем туда выбранный товар */
        ShoppingCart shoppingCart =(ShoppingCart) session.getAttribute(Constants.CURRENT_SHOPPING_CART);
        shoppingCart.addProduct(product,1);
        session.setAttribute(Constants.CURRENT_SHOPPING_CART, shoppingCart);
/** возвращаем из  sopingCart.html  фрагемент shopingCart */
        return "fragment/shopingCart :: shopingCart";
    }

}
