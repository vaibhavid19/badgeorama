package com.cognizant.badgeorama.service;

import com.cognizant.badgeorama.model.dto.ModelDto;
import com.cognizant.badgeorama.model.dto.RouteType;
import com.cognizant.badgeorama.restclient.MonitorRestClients;
import com.cognizant.badgeorama.restclient.VisitorRestClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Service
public class RouterService {

    private static final Logger logger = LoggerFactory.getLogger(RouterService.class.getName());

    private final MonitorRestClients monitorRestClients;
    private final VisitorRestClients visitorRestClients;

    public RouterService(MonitorRestClients monitorRestClients, VisitorRestClients visitorRestClients) {
        this.monitorRestClients = monitorRestClients;
        this.visitorRestClients = visitorRestClients;
    }

    public ModelDto route(ModelDto modelDto) {

        ModelDto response = null;

        if (modelDto.getDtoRoute().getRouteType() == RouteType.VISITOR) {

            Method method = null;
            String methodName = modelDto.getDtoRoute().getRestEndpointMethodName();

            try {
                method = this.visitorRestClients.getClass().getMethod(methodName, ModelDto.class);
            } catch (NoSuchMethodException e) {
                logger.error("No such method", e);
            }

            if (method != null) {
                try {
                    response = (ModelDto) method.invoke(this.visitorRestClients, modelDto);
                } catch (IllegalAccessException e) {
                    logger.error("IllegalAccessException", e);
                } catch (InvocationTargetException e) {
                    logger.error("InvocationTargetException", e);
                }
            }

        } else if (modelDto.getDtoRoute().getRouteType() == RouteType.MONITOR) {

            Method method = null;
            String methodName = modelDto.getDtoRoute().getRestEndpointMethodName();

            try {
                method = this.monitorRestClients.getClass().getMethod(methodName, ModelDto.class);
            } catch (NoSuchMethodException e) {
                logger.error("No such method", e);
            }

            if (method != null) {
                try {
                    response = (ModelDto) method.invoke(this.monitorRestClients, modelDto);
                } catch (IllegalAccessException e) {
                    logger.error("IllegalAccessException", e);
                } catch (InvocationTargetException e) {
                    logger.error("InvocationTargetException", e);
                }
            }

        }

        return response;

    }


}
