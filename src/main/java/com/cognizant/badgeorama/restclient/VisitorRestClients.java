package com.cognizant.badgeorama.restclient;

import com.cognizant.badgeorama.model.Visitor;
import com.cognizant.badgeorama.model.dto.ModelDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

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
        URI uri = getURIWithPathVariable(modelDto, phoneNumber);

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

        String protocol = env.getProperty("rest.client.protocol");
        String host = env.getProperty("rest.client.host");
        int port = Integer.parseInt(env.getProperty("rest.client.port"));

        String path = modelDto.getDtoRoute().getRestEndpoint();
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

    private URI getURIWithPathVariable(ModelDto modelDto, String variable) {

        String protocol = env.getProperty("rest.client.protocol");
        String host = env.getProperty("rest.client.host");
        int port = Integer.parseInt(env.getProperty("rest.client.port"));

        String endpoint = modelDto.getDtoRoute().getRestEndpoint();
        String path = new StringBuilder()
                .append(endpoint)
                .append(endpoint.endsWith("/") ? "" : "/")
                .append(variable).toString();
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
