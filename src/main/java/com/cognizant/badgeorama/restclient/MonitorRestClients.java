package com.cognizant.badgeorama.restclient;

import com.cognizant.badgeorama.model.Visitor;
import com.cognizant.badgeorama.model.dto.ModelDto;
import com.cognizant.badgeorama.util.RestClientUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

@Component
public class MonitorRestClients {

    private static final Logger logger = LoggerFactory.getLogger(VisitorRestClients.class);

    private final RestTemplate restTemplate;
    private final Environment env;

    public MonitorRestClients(RestTemplate restTemplate, Environment env) {
        this.restTemplate = restTemplate;
        this.env = env;
    }

    public ModelDto visitorAdmin(ModelDto modelDto) {

        URI uri = getURI(modelDto);

        restTemplate.put(uri, modelDto.getVisitor());

        return modelDto;

    }

    public ModelDto getWaitingVisitors(ModelDto modelDto) {

        return getListOfVisitors(modelDto);

    }

    public ModelDto getCheckedInVisitors(ModelDto modelDto) {

        return getListOfVisitors(modelDto);

    }

    public ModelDto getCheckedOutVisitors(ModelDto modelDto) {

        return getListOfVisitors(modelDto);

    }

    private URI getURI(ModelDto modelDto) {

        return getURI(modelDto, null);
    }

    private URI getURI(ModelDto modelDto, String variable) {

        return RestClientUtility.getUri(modelDto, variable, env);
    }

    private ModelDto getListOfVisitors(ModelDto modelDto) {


        URI uri = getURI(modelDto);

        ResponseEntity<List> response = restTemplate.getForEntity(uri, List.class);

        List<Visitor> visitors = response.getBody();
        modelDto.setVisitors(visitors);

        return modelDto;

    }
}


