package com.cognizant.badgeorama.restcontroller;

import com.cognizant.badgeorama.annotation.MonitorRestClient;
import com.cognizant.badgeorama.annotation.VisitorRestClient;
import com.cognizant.badgeorama.exception.DtoException;
import com.cognizant.badgeorama.model.Visitor;
import com.cognizant.badgeorama.model.dto.ModelDto;
import com.cognizant.badgeorama.model.dto.ModelDtoFactory;
import com.cognizant.badgeorama.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@RestController
public class AjaxController {

    private static final Logger logger = LoggerFactory.getLogger(AjaxController.class.getName());

    private final RouterService routerService;

    public AjaxController(RouterService routerService) {
        this.routerService = routerService;
    }

    @RequestMapping(value = "/time")
    public String getTime(Model model) {
        return new Date().toString();
    }

    @VisitorRestClient
    @RequestMapping(method = RequestMethod.POST, value = "/visitor/lookup")
    public Visitor visitorLookup(@RequestBody Visitor visitor) {

        // Get the dto
        ModelDto dto = getDto();

        // create/populate visitor and set in dto
        dto.setVisitor(visitor);

        // make call to router service
        ModelDto modelDto = routerService.route(dto);

        // get response from service and return it
        Visitor returnedVisitor = null;
        if (validDto(dto)) {
            returnedVisitor = modelDto.getResponse().getBody();
        } else {
            logger.error(findDtoIssue(dto));
        }

        return returnedVisitor;
    }



    @MonitorRestClient
    @RequestMapping(method = RequestMethod.GET, value = "/monitor/visitors")
    public List<Visitor> getVisitors() {

        List<Visitor> waiting = getWaitingVisitors();
        List<Visitor> in = getCheckedInVisitors();
        List<Visitor> out = getCheckedOutVisitors();

        List<Visitor> allVisitors = new ArrayList<>();

        allVisitors.addAll(waiting);
        allVisitors.addAll(in);
        allVisitors.addAll(out);

        //Collections.sort(allVisitors);

        return allVisitors;
    }

    @MonitorRestClient
    @RequestMapping(method = RequestMethod.GET, value = "/monitor/visitors/waiting")
    public List<Visitor> getWaitingVisitors() {

        // Get the dto
        ModelDto dto = getDto();

        ModelDto modelDto = routerService.route(dto);

        List<Visitor> visitors = modelDto.getVisitors();

        return visitors;
    }

    @MonitorRestClient
    @RequestMapping(method = RequestMethod.GET, value = "/monitor/visitors/checkedin")
    public List<Visitor> getCheckedInVisitors() {

        // Get the dto
        ModelDto dto = getDto();

        ModelDto modelDto = routerService.route(dto);

        List<Visitor> visitors = modelDto.getVisitors();

        return visitors;
    }

    @MonitorRestClient
    @RequestMapping(method = RequestMethod.GET, value = "/monitor/visitors/checkedout")
    public List<Visitor> getCheckedOutVisitors() {

        // Get the dto
        ModelDto dto = getDto();

        ModelDto modelDto = routerService.route(dto);

        List<Visitor> visitors = modelDto.getVisitors();

        return visitors;
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

    private String findDtoIssue(ModelDto dto) {

        String message = "Response DTO is valid.";

        if (dto.getResponse() == null) {

            message = "Response is null";

        }

        return message;
    }

    private boolean validDto(ModelDto dto) {

        return (dto.getResponse() != null) ? true : false;

    }


}
