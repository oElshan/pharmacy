package isha.store.controllers.client;

import isha.store.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;

@Controller
@RequestMapping(value = "/ajax/producers")
public class AjaxProducerController {

    @Autowired
    ServletContext servletContext;
    @GetMapping("/search")
    public ModelAndView searchProducerByNameLike(@RequestParam("name") String name, ModelMap modelMap) {

        System.out.println(name);
        modelMap.addAttribute("producers",servletContext.getAttribute(Constants.PRODUCER_LIST));
        return new ModelAndView("fragment/producer-list :: producer-list", modelMap);
    }
}
