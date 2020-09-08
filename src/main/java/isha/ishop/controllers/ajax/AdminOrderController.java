package isha.ishop.controllers.ajax;

import isha.ishop.entity.ClientOrder;
import isha.ishop.form.SearchForm;
import isha.ishop.model.OrderStatus;
import isha.ishop.services.impl.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminOrderController {

    @Autowired
    OrderServiceImpl orderService;

    @RequestMapping(path = "/ajax/admin/orderStatus", method = RequestMethod.GET, produces = "application/json; charset=UTF-8")
    @ResponseBody
    public OrderStatus deleteItemFromShoppingCartView(HttpSession session, Model model) {

//        List<ClientOrder> clientOrderList = orderService.getAllNewOrders();
        OrderStatus orderStatus = new OrderStatus();
        long count = orderService.getCountNewOrders("new");
        System.out.println("---------"+count);
        if (count > 0) {
            orderStatus.setMessage("You have new order!");
            orderStatus.setCount(count);

        }
        return orderStatus;
    }


    @RequestMapping( value = "/ajax/json/search-orders",method = RequestMethod.POST,produces = "application/json; charset=UTF-8" )
    public ModelAndView searchOrders(@RequestBody SearchForm search, ModelMap modelMap) {

        System.out.println(search);
        List<ClientOrder> orders = orderService.findOrderByName(search.getSearchName());
        modelMap.addAttribute("orders", orders);

        return new ModelAndView("fragment/dataTableOrders :: dataTableOrders", modelMap);
    }
}
