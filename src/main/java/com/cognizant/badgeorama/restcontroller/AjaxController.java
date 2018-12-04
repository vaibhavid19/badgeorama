package com.cognizant.badgeorama.restcontroller;

import com.cognizant.badgeorama.annotation.VisitorRestClient;
import com.cognizant.badgeorama.exception.DtoException;
import com.cognizant.badgeorama.model.Visitor;
import com.cognizant.badgeorama.model.dto.ModelDto;
import com.cognizant.badgeorama.model.dto.ModelDtoFactory;
import com.cognizant.badgeorama.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;


@RestController
public class AjaxController {

    private static final Logger logger = LoggerFactory.getLogger(AjaxController.class.getName());

    private final RouterService routerService;

    public AjaxController(RouterService routerService) {
        this.routerService = routerService;
    }

    @VisitorRestClient
    @RequestMapping(method = RequestMethod.POST, value = "/visitor/lookup")
    public Visitor visitorLookup(@RequestBody Visitor visitor) {

        // Get the dto
        ModelDto dto = getDto();

        // create/populate visitor and set in dto
        //Visitor visitor = Visitor.builder().phoneNumber(phoneNumber).build();
        dto.setVisitor(visitor);

        // make call to router service
        ModelDto modelDto = routerService.route(dto);

        // get response from service and return it
        //HttpStatus httpStatus = modelDto.getResponse().getStatusCode();
        //Map<String, Visitor> map = new HashMap<>();
        Visitor returnedVisitor = modelDto.getResponse().getBody();
        //map.put("visitor", returnedVisitor);

        return returnedVisitor;
    }

    @VisitorRestClient
    @RequestMapping(method = RequestMethod.POST, value = "/visitor/register")
    public ModelAndView registerVisitor(@ModelAttribute Visitor visitor) {

        // Get the dto
        ModelDto dto = getDto();

        // create/populate visitor and set in dto
        dto.setVisitor(visitor);

        // make call to router service
        ModelDto modelDto = routerService.route(dto);

        // get response from service and return it
        HttpStatus httpStatus = modelDto.getResponse().getStatusCode();

        return new ModelAndView("/visitor/visitor_checkin_success", httpStatus );

    }

    private ModelDto getDto() {

        // Get the dto
        ModelDto dto = null;
        try {
            dto = ModelDtoFactory.getInstance();
        } catch (DtoException e) {
            logger.error("Problem getting ModelDto.", e);
        }
        return dto;
    }


}
