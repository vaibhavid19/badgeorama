package com.cognizant.badgeorama.util;

import com.cognizant.badgeorama.model.dto.ModelDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;

import java.net.URI;
import java.net.URISyntaxException;

public class RestClientUtility {

    private static final Logger logger = LoggerFactory.getLogger(RestClientUtility.class.getName());

    public static URI getUri(ModelDto modelDto, String variable, Environment env) {

        String protocol = env.getProperty("rest.client.protocol");
        String host = env.getProperty("rest.client.host");
        int port = Integer.parseInt(env.getProperty("rest.client.port"));

        String endpoint = modelDto.getDtoRoute().getRestEndpoint();
        StringBuilder builder = new StringBuilder(endpoint);
        if (variable != null) {
            builder.append(endpoint.endsWith("/") ? "" : "/");
            builder.append(variable);
        }
        String path = builder.toString();

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
