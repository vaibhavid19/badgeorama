package com.cognizant.badgeorama.model.dto;

import com.cognizant.badgeorama.annotation.MonitorRestClient;
import com.cognizant.badgeorama.annotation.VisitorRestClient;
import com.cognizant.badgeorama.exception.DtoException;
import com.cognizant.badgeorama.service.RouteService;
import com.cognizant.badgeorama.util.SpringApplicationContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Map;

@Component
public class ModelDtoFactory {

    private static final Logger logger = LoggerFactory.getLogger(ModelDtoFactory.class.getName());


    public static ModelDto getInstance() throws DtoException {

        // create dto model objects
        final ModelDto dto = new GenericDto();
        final DtoRoute dtoRoute = new DtoRoute();

        // get names of calling method and class
        StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
        String callingMethodString;
        String fqn;
        try {
            callingMethodString = callStack[3].getMethodName();
            fqn = callStack[3].getClassName();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DtoException("Calling method not found.", e);
        }

        // setup route
        if (callingMethodString != null) {

            // get calling class
            Method method = null;
            Class<?> callingClass = null;
            try {
                callingClass = Class.forName(fqn);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }

            // get method - brute force, but gets job done
            Object bean = SpringApplicationContext.getBean(callingClass);
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method m : methods) {
                if (m.getName().equalsIgnoreCase(callingMethodString)) {
                    method = m;
                    break;
                }
            }

            // set ui endpoint method name
            dtoRoute.setUiEndpointMethodName(method.getName());

            // process annotations
            MonitorRestClient monitorRestClient;
            VisitorRestClient visitorRestClient;
            if (method != null) {

                // try to get annotations - returns null if not present
                monitorRestClient = method.getAnnotation(MonitorRestClient.class);
                visitorRestClient = method.getAnnotation(VisitorRestClient.class);


                // set route type and rest endpoint method name
                if (monitorRestClient != null && visitorRestClient == null) {

                    dtoRoute.setRouteType(RouteType.MONITOR);
                    String restClientMethod = monitorRestClient.value();
                    if ("".equals(restClientMethod)) {

                        dtoRoute.setRestEndpointMethodName(callingMethodString);

                    } else {

                        dtoRoute.setRestEndpointMethodName(restClientMethod);

                    }

                } else if (visitorRestClient != null && monitorRestClient == null) {

                    dtoRoute.setRouteType(RouteType.VISITOR);
                    String restClientMethod = visitorRestClient.value();
                    if ("".equals(restClientMethod)) {

                        dtoRoute.setRestEndpointMethodName(callingMethodString);

                    } else {

                        dtoRoute.setRestEndpointMethodName(restClientMethod);

                    }

                } else {
                    logger.error("Route type not set.  Use @MonitorRestClient or @VisitorRestClient annotation on UI rest controller method to mark route.");
                }

            }

            // set rest client endpoint - read from external properties file
            Map<String, String> routeTable = RouteService.getRouteTable();
            String endpoint = routeTable.get(callingMethodString);
            dtoRoute.setRestEndpoint(endpoint);

            // set route into dto
            dto.setDtoRoute(dtoRoute);
        }

        if (!validDtoRoute(dto)) {
            logger.error("Invalid Dto Route!  Probably caused by bad environment keys.  Check environment variable names.");
        }

        return dto;
    }

    private static boolean validDtoRoute(ModelDto modelDto) {

        boolean valid = true;

        DtoRoute route = modelDto.getDtoRoute();

        if (route.getRouteType() == null) {
            valid = false;
        }

        if (route.getRestEndpointMethodName() == null) {
            valid = false;
        }

        if (route.getRestEndpoint() == null) {
            valid = false;
        }

        if (route.getUiEndpointMethodName() == null) {
            valid = false;
        }

        return valid;
    }


}
