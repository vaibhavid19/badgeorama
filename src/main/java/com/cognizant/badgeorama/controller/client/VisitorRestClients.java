package com.cognizant.badgeorama.controller.client;

import com.cognizant.badgeorama.model.Visitor;
import com.cognizant.badgeorama.model.dto.ModelDto;
import com.cognizant.badgeorama.model.dto.VisitorLookupSparseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;


@Component
public class VisitorRestClients {

    private static final Logger logger = LoggerFactory.getLogger(VisitorRestClients.class);

    private final RestTemplate restTemplate;

    public VisitorRestClients(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ModelDto visitorLookup(ModelDto modelDto) {

        String phoneNumber = modelDto.getVisitor().getPhoneNumber();

        URI uri = getURI(phoneNumber);

        ResponseEntity<Visitor> response = restTemplate.getForEntity(uri, Visitor.class);

        modelDto.setResponse(response);

        return modelDto;
    }

    private URI getURI() {
        return getURI("");
    }

    private URI getURI(String phoneNumber) {

        String protocol = "http";
        String host = "localhost";
        int port = 15002;
        String path = "/visitor/lookup/" + phoneNumber;
        String auth = null;
        String fragment = null;
        String query = null;
        URI uri = null;
        try {
            uri = new URI(protocol, auth, host, port, path, query, fragment);
        } catch (URISyntaxException e) {
            logger.error("URI Error", e);
        }

        return uri;
    }
}
