package com.cognizant.badgeorama.controller;

import com.cognizant.badgeorama.configuration.Properties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;


// tag::code[]
@Controller
public class HomeController {

    private final Properties properties;

    public HomeController(Properties properties) {
        this.properties = properties;
    }

    @RequestMapping(value = "/")
    public String index(Model model) {

        model.addAttribute("location", properties.getLocation());
        model.addAttribute("time", new Date());


        return "index";
    }

}
// end::code[]