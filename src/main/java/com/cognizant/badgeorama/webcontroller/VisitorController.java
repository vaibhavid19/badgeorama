package com.cognizant.badgeorama.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// tag::code[]
@Controller
public class VisitorController {

    @RequestMapping(value = "/visitor")
    public String visitor() {
        return "visitor/visitor";
    }

    @RequestMapping(value = "/checkin")
    public String checkin() {
        return "visitor/visitor_checkin";
    }

    @RequestMapping(value = "/checkout")
    public String checkout() {
        return "visitor/visitor_checkout";
    }


}
// end::code[]