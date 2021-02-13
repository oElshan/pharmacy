package isha.store.services.impl;

import isha.store.model.ShoppingCart;
import isha.store.model.ShoppingCartItem;
import isha.store.services.WebService;
import isha.store.utils.Constants;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class WebServiceImpl  implements WebService {


    @Override
    public String serializeShoppingCart(ShoppingCart shoppingCart) {
        StringBuilder res = new StringBuilder();
        for (ShoppingCartItem item : shoppingCart.getItems()) {
            res.append(item.getProduct().getId()).append("-").append(item.getCount()).append("|");
        }
        if (res.length() > 0) {
            res.deleteCharAt(res.length() - 1);
        }
        return res.toString();
    }

    @Override
    public ShoppingCart getCurrentShoppingCart(HttpServletRequest req) {
        return (ShoppingCart) req.getSession().getAttribute(Constants.CURRENT_SHOPPING_CART);
    }
    @Override
    public boolean isCurrentShoppingCartCreated(HttpServletRequest req) {
        return req.getSession().getAttribute(Constants.CURRENT_SHOPPING_CART) != null;
    }
    @Override
    public void setCurrentShoppingCart(HttpServletRequest req, ShoppingCart shoppingCart) {
        req.getSession().setAttribute(Constants.CURRENT_SHOPPING_CART, shoppingCart);
    }
    @Override
    public void clearCurrentShoppingCart(HttpServletRequest req, HttpServletResponse resp) {
        req.getSession().removeAttribute(Constants.CURRENT_SHOPPING_CART);
        setCookie(Constants.Cookie.SHOPPING_CART.getName(), null, 0, resp);
    }
    @Override
    public Cookie findShoppingCartCookie(HttpServletRequest req) {
        return findCookie(req, Constants.Cookie.SHOPPING_CART.getName());
    }
    @Override
    public  void updateCurrentShoppingCartCookie(String cookieValue, HttpServletResponse resp) {
        setCookie(Constants.Cookie.SHOPPING_CART.getName(), cookieValue,
                Constants.Cookie.SHOPPING_CART.getTtl(), resp);
    }
    @Override
    public  Cookie findCookie(HttpServletRequest req, String cookieName) {
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if (c.getName().equals(cookieName)) {
                    if (c.getValue() != null && !"".equals(c.getValue())) {
                        return c;
                    }
                }
            }
        }
        return null;
    }
    @Override
    public  void setCookie(String name, String value, int age, HttpServletResponse resp) {
        Cookie c = new Cookie(name, value);
        c.setMaxAge(age);
        c.setPath("/");
        c.setHttpOnly(true);
        resp.addCookie(c);
    }

    @Override
    public String getCurrentRequestUrl(HttpServletRequest req) {
        String query = req.getQueryString();
        if (query == null) {
            return req.getRequestURI();
        } else {
            return req.getRequestURI() + "?" + query;
        }
    }






//    public static CurrentAccount getCurrentAccount(HttpServletRequest req) {
//        return (CurrentAccount) req.getSession().getAttribute(Constants.CURRENT_ACCOUNT);
//    }

//    public static void setCurrentAccount(HttpServletRequest req, CurrentAccount currentAccount) {
//        req.getSession().setAttribute(Constants.CURRENT_ACCOUNT, currentAccount);
//    }
//
//    public static boolean isCurrentAccountCreated(HttpServletRequest req) {
//        return getCurrentAccount(req) != null;
//    }
//

}
