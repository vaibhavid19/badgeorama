package com.cognizant.badgeorama.webcontroller;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class HomeController {

    private final Environment env;

    public HomeController(Environment env) {
        this.env = env;
    }

    @RequestMapping(value = "/")
    public String index(Model model) {

        model.addAttribute("location", env.getProperty("process.env.LOCATION"));

        return "index";
    }

    @RequestMapping(value = "/printbadge")
    public String printBadge(Model model) {


        return "printbadge";
    }

}
