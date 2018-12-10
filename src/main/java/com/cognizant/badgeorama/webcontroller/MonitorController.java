package com.cognizant.badgeorama.webcontroller;

import com.cognizant.badgeorama.annotation.MonitorRestClient;
import com.cognizant.badgeorama.exception.DtoException;
import com.cognizant.badgeorama.model.Visitor;
import com.cognizant.badgeorama.model.VisitorAdmin;
import com.cognizant.badgeorama.model.Visitors;
import com.cognizant.badgeorama.model.dto.ModelDto;
import com.cognizant.badgeorama.model.dto.ModelDtoFactory;
import com.cognizant.badgeorama.service.RouterService;
import org.apache.commons.text.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
public class MonitorController {

    private static final Logger logger = LoggerFactory.getLogger(MonitorController.class.getName());

    private final Environment env;
    private final RouterService routerService;

    public MonitorController(Environment env, RouterService routerService) {
        this.env = env;
        this.routerService = routerService;
    }

    @ModelAttribute("visitors")
    public Visitors getVisitor() {

        Visitors visitors = new Visitors();

        return visitors;
    }

    @RequestMapping(value = "/monitor")
    public String monitor(Model model) {

        model.addAttribute("location", env.getProperty("process.env.LOCATION"));

        return "monitor/monitor";
    }

    @RequestMapping(value = "/status")
    public String status() {
        return "monitor/visitor_status";
    }

    @RequestMapping(value = "/admin")
    public ModelAndView admin(Model model) {

        // List of statuses for form
        List<String> statuses = new ArrayList<>();
        for (Visitor.VisitStatus status : Visitor.VisitStatus.values()) {
            String statusDisplay = WordUtils.capitalizeFully(status.name(), '_');
            statusDisplay = statusDisplay.replace("_", " ");
            statuses.add(statusDisplay);
        }
        String[] statusArray = new String[statuses.size()];
        statusArray = statuses.toArray(statusArray);

        // List of visitor types for form
        List<String> visitorTypes = new ArrayList<>();
        for (Visitor.VisitorType type : Visitor.VisitorType.values()) {
            String typeDisplay = WordUtils.capitalizeFully(type.name(), '_');
            typeDisplay = typeDisplay.replace("_", " ");
            visitorTypes.add(typeDisplay);
        }
        String[] typeArray = new String[visitorTypes.size()];
        typeArray = visitorTypes.toArray(typeArray);


        VisitorAdmin visitorAdmin = new VisitorAdmin();

        model.addAttribute("listOfVisitorStatuses", statusArray);
        model.addAttribute("listOfVisitorTypes", typeArray);

        model.addAttribute("badgeNumber", "1234");

        return new ModelAndView("monitor/visitor_admin", "visitor", visitorAdmin);

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

    @RequestMapping(method = RequestMethod.POST, value = "/printbadge")
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
