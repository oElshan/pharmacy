package isha.ishop.listener;

import isha.ishop.services.ProductService;
import isha.ishop.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.ContextStartedEvent;
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
        servletContext.setAttribute(Constants.PRODUCER_LIST, productService.findAllProducer());

    }


}
