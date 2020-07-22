package isha.ishop.controllers.ajax;

import com.fasterxml.jackson.annotation.JsonView;
import isha.ishop.entity.Product;
import isha.ishop.model.ShoppingCart;
import isha.ishop.services.ProductService;
import isha.ishop.utils.Constants;
import isha.ishop.utils.Views;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

/**
 * Ajax запрос на добавление товара в корзину
 * @author oelshan
 * @see http://devstudy.net
 */
@Controller
public class AddProductController {

    @Autowired
    ProductService productService;

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
