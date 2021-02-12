package isha.ishop.interceptors;

import isha.ishop.entity.Product;
import isha.ishop.model.ShoppingCart;
import isha.ishop.services.ProductService;
import isha.ishop.services.WebService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ShoppingCartInterceptor extends HandlerInterceptorAdapter {


    @Autowired
    WebService webService;

    @Autowired
    ProductService productService;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        ShoppingCart shoppingCart;

        if (!webService.isCurrentShoppingCartCreated(request)) {

            Cookie cookieShoppingCart = webService.findShoppingCartCookie(request);
            if (cookieShoppingCart != null) {
              shoppingCart = deserializeShoppingCart(cookieShoppingCart.getValue());
            } else {
              shoppingCart = new ShoppingCart();
            }
            webService.setCurrentShoppingCart(request, shoppingCart);

        }

        return super.preHandle(request, response, handler);
    }

    public ShoppingCart deserializeShoppingCart(String string) {
        ShoppingCart shoppingCart = new ShoppingCart();
        String[] items = string.split("\\|");
        for (String item : items) {
            try {
                String data[] = item.split("-");
                long idProduct = Long.parseLong(data[0]);
                int count = Integer.parseInt(data[1]);
                Product product = productService.findProductById(idProduct);
                shoppingCart.addProduct(product, count);
            } catch (RuntimeException e) {
//                LOGGER.error("Can't add product to ShoppingCart during deserialization: item=" + item, e);
            }
        }
//        return shoppingCart.getItems().isEmpty() ? null : shoppingCart;
        return  shoppingCart;
    }
}
