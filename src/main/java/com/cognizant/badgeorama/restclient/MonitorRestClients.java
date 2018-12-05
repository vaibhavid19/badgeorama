package com.cognizant.badgeorama.restclient;

import com.cognizant.badgeorama.configuration.GeneralProperties;
import com.cognizant.badgeorama.model.dto.ModelDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Component
public class MonitorRestClients {

    private static final Logger logger = LoggerFactory.getLogger(VisitorRestClients.class);

    private final RestTemplate restTemplate;
    private final GeneralProperties properties;

    public MonitorRestClients(RestTemplate restTemplate, GeneralProperties properties) {
        this.restTemplate = restTemplate;
        this.properties = properties;
    }

    public ModelDto visitorAdmin(ModelDto modelDto) {

        URI uri = getURI(modelDto);

        restTemplate.put(uri, modelDto.getVisitor());

        return modelDto;

    }

    private URI getURI(ModelDto modelDto) {

        String protocol = properties.getProtocol();
        String host = properties.getHost();
        int port = Integer.parseInt(properties.getPort());

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

        String protocol = properties.getProtocol();
        String host = properties.getHost();
        int port = Integer.parseInt(properties.getPort());

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
