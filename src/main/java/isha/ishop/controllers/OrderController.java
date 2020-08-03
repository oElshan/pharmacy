package isha.ishop.controllers;

import isha.ishop.entity.ClientOrder;
import isha.ishop.form.OrderForm;
import isha.ishop.model.ShoppingCart;
import isha.ishop.services.ClientService;
import isha.ishop.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.math.BigDecimal;

@Controller
public class OrderController {

    @Autowired
    ClientService clientService;


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


}
