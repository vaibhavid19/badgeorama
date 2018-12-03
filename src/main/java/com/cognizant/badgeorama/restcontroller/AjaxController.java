package com.cognizant.badgeorama.restcontroller;

import com.cognizant.badgeorama.exception.DtoException;
import com.cognizant.badgeorama.model.Visitor;
import com.cognizant.badgeorama.model.dto.ModelDto;
import com.cognizant.badgeorama.model.dto.ModelDtoFactory;
import com.cognizant.badgeorama.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@RestController
public class AjaxController {

    private static final Logger logger = LoggerFactory.getLogger(AjaxController.class.getName());

    private final RouterService routerService;

    public AjaxController(RouterService routerService) {
        this.routerService = routerService;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/visitor/lookup/{phoneNumber}")
    public Visitor getVisitorLookup(@PathVariable("phoneNumber") String phoneNumber, Model model) {

        // Get the dto
        ModelDto dto = null;
        try {
            dto = ModelDtoFactory.getInstance();
        } catch (DtoException e) {
            logger.error("Problem getting ModelDto.", e);
        }

        // create/populate visitor and set in dto
        Visitor requestVisitor = new Visitor.VisitorBuilder().phoneNumber(phoneNumber).build();
        dto.setVisitor(requestVisitor);

        // make call to router service
        ModelDto modelDto = routerService.route(dto);

        // get response from service and return it
        Visitor response = modelDto.getResponse().getBody();
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value="/visitor/register")
    public Visitor registerVisitor(@RequestBody Visitor visitor, Model model) {

        // Get the dto
        ModelDto dto = null;
        try {
            dto = ModelDtoFactory.getInstance();
        } catch (DtoException e) {
            logger.error("Problem getting ModelDto.", e);
        }
        // create/populate visitor and set in dto
        dto.setVisitor(visitor);

        // make call to router service
        ModelDto modelDto = routerService.route(dto);

        // get response from service and return it
        Visitor response = modelDto.getResponse().getBody();
        return response;

    }


}
