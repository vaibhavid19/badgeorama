package com.cognizant.badgeorama.webcontroller;

import com.cognizant.badgeorama.model.Visitor;
import com.cognizant.badgeorama.model.VisitorAdmin;
import com.cognizant.badgeorama.model.Visitors;
import org.apache.commons.text.WordUtils;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;


@Controller
public class MonitorController {

    private final Environment env;

    public MonitorController(Environment env) {
        this.env = env;
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


}
