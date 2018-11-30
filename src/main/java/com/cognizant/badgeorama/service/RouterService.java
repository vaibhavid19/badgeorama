package com.cognizant.badgeorama.service;

import com.cognizant.badgeorama.controller.client.MonitorRestClients;
import com.cognizant.badgeorama.controller.client.VisitorRestClients;
import com.cognizant.badgeorama.model.dto.ModelDto;
import com.cognizant.badgeorama.model.dto.VisitorLookupDenseDto;
import com.cognizant.badgeorama.model.dto.VisitorLookupSparseDto;
import org.springframework.stereotype.Service;

@Service
public class RouterService {

    private final MonitorRestClients monitorRestClients;
    private final VisitorRestClients visitorRestClients;

    public RouterService(MonitorRestClients monitorRestClients, VisitorRestClients visitorRestClients) {
        this.monitorRestClients = monitorRestClients;
        this.visitorRestClients = visitorRestClients;
    }

    public ModelDto route(ModelDto modelDto) {

        ModelDto response = null;

        if (modelDto instanceof VisitorLookupSparseDto) {

            response = visitorRestClients.visitorLookup(modelDto);

        } else if (modelDto instanceof VisitorLookupDenseDto) {


        }

        return response;

    }


}
