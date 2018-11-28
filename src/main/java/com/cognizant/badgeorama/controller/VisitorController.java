package com.cognizant.badgeorama.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/visitor")
public class VisitorController {


    @RequestMapping(value = "/")
    public String visitor() {
        return "visitor";
    }



}
