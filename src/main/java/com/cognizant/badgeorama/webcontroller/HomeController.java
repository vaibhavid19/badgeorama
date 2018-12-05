package com.cognizant.badgeorama.webcontroller;

import com.cognizant.badgeorama.configuration.GeneralProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;


@Controller
public class HomeController {

    private final GeneralProperties properties;

    public HomeController(GeneralProperties properties) {
        this.properties = properties;
    }

    @RequestMapping(value = "/")
    public String index(Model model) {

        model.addAttribute("location", properties.getLocation());

        return "index";
    }

}
