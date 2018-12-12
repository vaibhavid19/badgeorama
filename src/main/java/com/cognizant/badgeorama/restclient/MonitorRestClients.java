package com.cognizant.badgeorama.restclient;

import com.cognizant.badgeorama.model.Visitor;
import com.cognizant.badgeorama.model.dto.ModelDto;
import com.cognizant.badgeorama.util.RestClientUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
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

        logger.info("Hitting endpoint " + uri.toASCIIString());
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

    public ModelDto visitLookup(ModelDto modelDto) {

        String phoneNumber = modelDto.getVisitor().getPhoneNumber();
        URI uri = getURI(modelDto, phoneNumber);

        logger.info("Hitting endpoint " + uri.toASCIIString());

        displayJson(modelDto);

        ResponseEntity<Visitor> response = restTemplate.getForEntity(uri, Visitor.class);

        displayResponse(response);

        modelDto.setResponse(response);

        return modelDto;
    }

    private URI getURI(ModelDto modelDto) {

        return getURI(modelDto, null);
    }

    private URI getURI(ModelDto modelDto, String variable) {

        return RestClientUtility.getUri(modelDto, variable, env);
    }

    private ModelDto getListOfVisitors(ModelDto modelDto) {


        URI uri = getURI(modelDto);

        logger.info("Hitting endpoint " + uri.toASCIIString());
        ResponseEntity<List> response = restTemplate.getForEntity(uri, List.class);

        List<Visitor> visitors = response.getBody();
        modelDto.setVisitors(visitors);

        return modelDto;

    }

    private void displayJson(ModelDto modelDto) {

        String json = null;
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

        try {
            json = objectMapper.writeValueAsString(modelDto.getVisitor());
        } catch (JsonProcessingException e) {
            logger.warn("Issue parsing JSON", e);
        }
        logger.info("Sending data:\n" + json);

    }

    private void displayResponse(ResponseEntity<Visitor> response) {

        if (response != null) {

            String json = null;
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.enable(SerializationFeature.INDENT_OUTPUT);

            try {
                json = objectMapper.writeValueAsString(response.getBody());
            } catch (JsonProcessingException e) {
                logger.warn("Issue parsing JSON", e);
            } catch (NullPointerException e) {
                logger.warn("Dave, What are you doing Dave!?  You have a NullPointerException.");
            }
            logger.info("Received data:\n" + json);
            logger.info("Received status " + response.getStatusCode());

        } else {

            logger.warn("I can't let you do that, Dave!  The expected response is NULL");

        }

    }
}


