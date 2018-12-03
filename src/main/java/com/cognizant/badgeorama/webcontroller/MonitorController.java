package com.cognizant.badgeorama.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

// tag::code[]
@Controller
public class MonitorController {

    @RequestMapping(value = "/monitor")
    public String monitor() {
        return "monitor/monitor";
    }

    @RequestMapping(value = "/status")
    public String status() {
        return "monitor/visitor_status";
    }

    @RequestMapping(value = "/admin")
    public String admin() {
        return "monitor/visitor_admin";
    }


}
// end::code[]