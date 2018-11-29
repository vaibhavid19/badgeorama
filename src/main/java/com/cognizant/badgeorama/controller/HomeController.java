package com.cognizant.badgeorama.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping(value = "/")
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/visitor")
    public String visitor() {
        return "visitor";
    }

    @RequestMapping(value = "/monitor")
    public String monitor() {
        return "monitor";
    }
}
