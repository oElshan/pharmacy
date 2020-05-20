package isha.ishop.interceptors;

import isha.ishop.services.ProductService;
import isha.ishop.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CategoryProducerInterceptor extends HandlerInterceptorAdapter {


    @Autowired
    ProductService productService;

    @Autowired
    ServletContext servletContext;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        request.getSession().getAttribute(Constants.CATEGORY_LIST);

        request.getSession().getAttribute(Constants.PRODUCER_LIST);



        return super.preHandle(request, response, handler);
    }
}
