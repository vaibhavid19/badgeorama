package com.cognizant.badgeorama.controller;

import com.cognizant.badgeorama.model.Visitor;
import com.cognizant.badgeorama.model.dto.ModelDto;
import com.cognizant.badgeorama.model.dto.VisitorLookupSparseDto;
import com.cognizant.badgeorama.service.RouterService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class AjaxController {

    private final RouterService routerService;

    public AjaxController(RouterService routerService) {
        this.routerService = routerService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/visitor/lookup/{phoneNumber}")
    public Visitor getVisitorLookup(@PathVariable("phoneNumber") String phoneNumber, Model model) {

        VisitorLookupSparseDto dto = new VisitorLookupSparseDto(phoneNumber);
        ModelDto modelDto = routerService.route(dto);
        Visitor visitor = modelDto.getResponse().getBody();
        return visitor;
    }


}
