package com.cognizant.badgeorama.model.dto;

import com.cognizant.badgeorama.exception.DtoException;
import com.cognizant.badgeorama.service.RouteService;
import org.springframework.stereotype.Component;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@Component
public class ModelDtoFactory {

    public static ModelDto getInstance() throws DtoException {

        ModelDto dto = null;

        // get calling method name
        StackTraceElement[] callStack = Thread.currentThread().getStackTrace();
        String callingMethod;
        try {
            callingMethod = callStack[2].getMethodName();
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DtoException("Calling method not found.", e);
        }

        if (callingMethod != null) {

            // get DTO route
            Map<String, DtoRoute> routeTable = RouteService.getRouteTable();
            DtoRoute route = routeTable.get(callingMethod);

            // get DTO class instance
            try {
                Class<?> cls = Class.forName(route.getDtoClass());
                Constructor<?> constructor = cls.getConstructor();
                dto = (ModelDto) constructor.newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

            // set route
            dto.setDtoRoute(route);
        }

        return dto;
    }


}
