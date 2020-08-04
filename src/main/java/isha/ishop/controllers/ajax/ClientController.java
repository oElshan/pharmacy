package isha.ishop.controllers.ajax;

import com.fasterxml.jackson.annotation.JsonView;
import isha.ishop.entity.ClientOrder;
import isha.ishop.entity.Product;
import isha.ishop.form.OrderForm;
import isha.ishop.model.ShoppingCart;
import isha.ishop.services.ClientService;
import isha.ishop.services.ProductService;
import isha.ishop.utils.Constants;
import isha.ishop.utils.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
public class ClientController {

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
        System.out.println(clientOrder);
        model.addAttribute("totalCost", shoppingCart.getTotalCost().doubleValue());

        shoppingCart.getItems().clear();
        shoppingCart.setTotalCost(BigDecimal.ZERO);
        shoppingCart.setTotalCount(0);
        session.setAttribute(Constants.CURRENT_SHOPPING_CART,shoppingCart);
        model.addAttribute("clientOrder", clientOrder);
        return "fragment/orderComplete :: orderComplete";
    }



    /**
     * Метод добовляет товар в корзину( ShoppingCart) и возвращает шаблон представления sopingCart.html
     *
     * @param idProduct
     * @param session
     * @return
     */

    @JsonView(Views.Public.class)
    @GetMapping("/ajax/json/product/add")
    public String addShopingCart(@RequestParam("idProduct") long idProduct, HttpSession session) {

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
