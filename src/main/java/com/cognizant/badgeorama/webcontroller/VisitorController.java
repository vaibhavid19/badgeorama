package com.cognizant.badgeorama.webcontroller;

import com.cognizant.badgeorama.model.Visitor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

// tag::code[]
@Controller
public class VisitorController {

    @RequestMapping(value = "/visitor")
    public String visitor() {
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
// end::code[]