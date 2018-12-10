package com.cognizant.badgeorama.restcontroller;

import com.cognizant.badgeorama.annotation.MonitorRestClient;
import com.cognizant.badgeorama.annotation.VisitorRestClient;
import com.cognizant.badgeorama.exception.DtoException;
import com.cognizant.badgeorama.model.Visitor;
import com.cognizant.badgeorama.model.VisitorAdmin;
import com.cognizant.badgeorama.model.dto.ModelDto;
import com.cognizant.badgeorama.model.dto.ModelDtoFactory;
import com.cognizant.badgeorama.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

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
        Visitor returnedVisitor = modelDto.getResponse().getBody();

        return returnedVisitor;
    }

    @VisitorRestClient
    @RequestMapping(method = RequestMethod.POST, value = "/visitor/register")
    public ModelAndView registerVisitor(@ModelAttribute Visitor visitor) {

        // Get the dto
        ModelDto dto = getDto();

        // create/populate visitor and set in dto
        visitor.setStatus(Visitor.VisitStatus.UNVERIFIED);
        dto.setVisitor(visitor);

        // make call to router service
        ModelDto modelDto = routerService.route(dto);

        // get response from service and return it
        HttpStatus httpStatus = modelDto.getResponse().getStatusCode();

        return new ModelAndView("/visitor/visitor_checkin_success", httpStatus);

    }

    @VisitorRestClient
    @RequestMapping(method = RequestMethod.POST, value = "/visitor/checkout")
    public ModelAndView checkoutVisitor(@ModelAttribute Visitor visitor) {

        // Get the dto
        ModelDto dto = getDto();

        // create/populate visitor and set in dto
        visitor.setStatus(Visitor.VisitStatus.OUT);
        dto.setVisitor(visitor);

        // make call to router service
        routerService.route(dto);

        return new ModelAndView("/visitor/visitor_checkout_success", HttpStatus.OK);

    }

    @MonitorRestClient
    @RequestMapping(method = RequestMethod.POST, value = "/visitor/admin")
    public ModelAndView visitorAdmin(@ModelAttribute VisitorAdmin visitorAdmin) {

        // Get the dto
        ModelDto dto = getDto();

        // create/populate visitor and set in dto
        Visitor visitor = new Visitor(
                visitorAdmin.getPhoneNumber(),
                visitorAdmin.getFirstName(),
                visitorAdmin.getLastName(),
                visitorAdmin.getCompany(),
                visitorAdmin.getHostName(),
                visitorAdmin.getHostPhone(),
                visitorAdmin.getPurposeOfVisit(),
                visitorAdmin.getCheckedInBy(),
                visitorAdmin.getCheckedOutBy(),
                visitorAdmin.getReasonForDeletion(),
                visitorAdmin.getBadgeNumber(),
                visitorAdmin.getRegisterDate(),
                visitorAdmin.getCheckedInDate(),
                visitorAdmin.getCheckedOutDate(),
                visitorAdmin.getMilliSecondsSinceRegistration(),
                visitorAdmin.getActive(),
                visitorAdmin.getStatus(),
                visitorAdmin.getVisitorType()
        );

        // set selected status
        String selectedStatus = visitorAdmin.getVisitorStatusSelectedValue();
        selectedStatus = selectedStatus.replace(" ", "_");
        selectedStatus = selectedStatus.toUpperCase();
        Visitor.VisitStatus status = Visitor.VisitStatus.valueOf(selectedStatus);
        visitor.setStatus(status);

        // set selected visit type
        String selectedType = visitorAdmin.getVisitorTypeSelectedValue();
        selectedType = selectedType.replace(" ", "_");
        selectedType = selectedType.toUpperCase();
        Visitor.VisitorType visitorType = Visitor.VisitorType.valueOf(selectedType);
        visitor.setVisitorType(visitorType);

        dto.setVisitor(visitor);

        // make call to router service
        routerService.route(dto);

        return new ModelAndView("/monitor/visitor_admin_success", HttpStatus.OK);

    }

    @MonitorRestClient
    @RequestMapping(method = RequestMethod.GET, value = "/monitor/visitors")
    public List<Visitor> getVisitors() {

        List<Visitor> waiting = getWaitingVisitors();
        List<Visitor> in = getCheckedInVisitors();
        List<Visitor> out = getCheckedOutVisitors();

        List<Visitor> all = new ArrayList<>();

        all.addAll(waiting);
        all.addAll(in);
        all.addAll(out);

        return all;
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

    @RequestMapping(method = RequestMethod.POST, value="/printbadge")
    public ModelAndView printBadge(@RequestBody Visitor visitor, Model model) {


        model.addAttribute("visitor", visitor);
        return new ModelAndView("/printbadge", HttpStatus.OK);


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
