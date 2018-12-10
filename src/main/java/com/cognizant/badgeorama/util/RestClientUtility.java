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

        String protocol = env.getProperty("process.env.PROTOCOL");
        String host = env.getProperty("process.env.HOST");
        int port = 0;
        if ("https".equalsIgnoreCase(protocol)) {
            port = 443;
        } else {
            port = Integer.parseInt(env.getProperty("process.env.PORT"));
        }

        String endpoint = modelDto.getDtoRoute().getRestEndpoint();
        StringBuilder builder = new StringBuilder();
        builder.append(endpoint);
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
