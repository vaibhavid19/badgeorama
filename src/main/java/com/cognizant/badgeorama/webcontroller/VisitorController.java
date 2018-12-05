package com.cognizant.badgeorama.webcontroller;

import com.cognizant.badgeorama.configuration.GeneralProperties;
import com.cognizant.badgeorama.model.Visitor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class VisitorController {

    private final GeneralProperties properties;

    public VisitorController(GeneralProperties properties) {
        this.properties = properties;
    }

    @RequestMapping(value = "/visitor")
    public String visitor(Model model) {

        model.addAttribute("location", properties.getLocation());

        return "visitor/visitor";
    }

    @RequestMapping(value = "/checkin")
    public String checkin(Model model) {
        model.addAttribute("visitor", new Visitor());
        return "visitor/visitor_checkin";
    }

    @RequestMapping(value = "/checkout")
    public String checkout(Model model) {
        model.addAttribute("visitor", new Visitor());
        return "visitor/visitor_checkout";
    }


}
