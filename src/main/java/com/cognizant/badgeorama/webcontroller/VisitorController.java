package com.cognizant.badgeorama.webcontroller;

import com.cognizant.badgeorama.annotation.VisitorRestClient;
import com.cognizant.badgeorama.exception.DtoException;
import com.cognizant.badgeorama.model.Visitor;
import com.cognizant.badgeorama.model.dto.ModelDto;
import com.cognizant.badgeorama.model.dto.ModelDtoFactory;
import com.cognizant.badgeorama.service.RouterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class VisitorController {

    private static final Logger logger = LoggerFactory.getLogger(VisitorController.class.getName());

    private final Environment env;
    private final RouterService routerService;

    public VisitorController(Environment env, RouterService routerService) {
        this.env = env;
        this.routerService = routerService;
    }

    @RequestMapping(value = "/visitor")
    public String visitor(Model model) {

        model.addAttribute("location", env.getProperty("process.env.LOCATION"));

        return "visitor/visitor";
    }

    @RequestMapping(value = "/checkin")
    public String checkin(Model model) {
        model.addAttribute("visitor", new Visitor());
        return "visitor/visitor_checkin";
    }

    @RequestMapping(value = "/checkout")
    public String checkout(Model model) {
        model.addAttribute("visitor", new Visitor());
        return "visitor/visitor_checkout";
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
        HttpStatus httpStatus = HttpStatus.OK;
        if (validDto(dto)) {
            httpStatus = modelDto.getResponse().getStatusCode();
        } else {
            logger.error(findDtoIssue(dto));
        }

        return new ModelAndView("visitor/visitor_checkin_success", httpStatus);

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

        return new ModelAndView("visitor/visitor_checkout_success", HttpStatus.OK);

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
