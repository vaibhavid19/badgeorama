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

/**
 * Trying to follow RFC 2616 with these endpoints.
 */
@Component
public class VisitorRestClients {

    private static final Logger logger = LoggerFactory.getLogger(VisitorRestClients.class);

    private final RestTemplate restTemplate;
    private final Environment env;

    public VisitorRestClients(RestTemplate restTemplate, Environment env) {
        this.restTemplate = restTemplate;
        this.env = env;
    }

    public ModelDto visitorLookup(ModelDto modelDto) {

        String phoneNumber = modelDto.getVisitor().getPhoneNumber();
        URI uri = getURI(modelDto, phoneNumber);

        ResponseEntity<Visitor> response = restTemplate.getForEntity(uri, Visitor.class);
        modelDto.setResponse(response);

        return modelDto;
    }

    public ModelDto registerVisitor(ModelDto modelDto) {

        URI uri = getURI(modelDto);

        ResponseEntity<Visitor> response = restTemplate.postForEntity(uri, modelDto.getVisitor(), Visitor.class);
        modelDto.setResponse(response);

        return modelDto;
    }

    public ModelDto checkoutVisitor(ModelDto modelDto) {

        URI uri = getURI(modelDto);

        restTemplate.put(uri, modelDto.getVisitor());

        return modelDto;
    }

    private URI getURI(ModelDto modelDto) {

        return getURI(modelDto, null);
    }

    private URI getURI(ModelDto modelDto, String variable) {

        return RestClientUtility.getUri(modelDto, variable, env);
    }

}
