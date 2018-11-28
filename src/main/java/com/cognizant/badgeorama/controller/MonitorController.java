package com.cognizant.badgeorama.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/monitor")
public class MonitorController {

    @RequestMapping(value = "/")
    public String guard() {
        return "monitor";
    }
}
