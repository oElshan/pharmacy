package isha.store.listener;

import isha.store.services.ProductService;
import isha.store.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;

@Component
public class ContextOnListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    ProductService productService;

    @Autowired
    ServletContext servletContext;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        servletContext.setAttribute(Constants.CATEGORY_LIST,productService.findAllCategory());
        servletContext.setAttribute(Constants.SUBCATEGORY_LIST, productService.findAllSubCategory());
        servletContext.setAttribute(Constants.PRODUCER_LIST, productService.findAllProducer());
        servletContext.setAttribute(Constants.SPECCATEGORY_LIST, productService.listAllSpecCategory());

    }


}
