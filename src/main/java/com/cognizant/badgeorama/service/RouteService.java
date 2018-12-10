package com.cognizant.badgeorama.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.ResourcePropertySource;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Uses Spring Environment to pull values from the route.properties file.  Endpoints
 * that start with route.endpoint end up in the route table.
 */
@Service
public class RouteService {

    private static final Logger logger = LoggerFactory.getLogger(RouteService.class.getName());

    private static HashMap<String, String> endpoints;

    private final Environment env;

    public RouteService(Environment env) {

        this.env = env;

        endpoints = new HashMap<>();

        Map<String, Object> map = new HashMap();

        for (Iterator it = ((AbstractEnvironment) this.env).getPropertySources().iterator(); it.hasNext(); ) {
            PropertySource propertySource = (PropertySource) it.next();

            if (propertySource instanceof MapPropertySource && propertySource.getName().contains("application.properties")) {

                map.putAll(((MapPropertySource) propertySource).getSource());

            }
        }

        // we only want the last part of the environment key as a key in our route table
        int counter=0;
        for (String key : map.keySet()) {

            if (key.contains("ROUTE_")) {

                String[] tokens = key.split("[.]");
                String[] tokens2 = tokens[tokens.length - 1].split("_");
                String newKey = tokens2[tokens2.length - 1];

                endpoints.put(newKey, map.get(key).toString());
                counter++;
            }
        }
        logger.info("Saved " + counter + " PATH keys from application.properties");

        map.clear();
        for (Iterator it = ((AbstractEnvironment) this.env).getPropertySources().iterator(); it.hasNext(); ) {
            PropertySource propertySource = (PropertySource) it.next();
            if (propertySource instanceof MapPropertySource && propertySource.getName().contains("systemEnvironment")) {

                map.putAll(((MapPropertySource) propertySource).getSource());

            }
        }

        // we only want the last part of the environment key as a key in our route table
        counter=0;
        for (String key : map.keySet()) {

            if (key.contains("ROUTE_")) {

                String[] tokens = key.split("[.]");
                String[] tokens2 = tokens[tokens.length - 1].split("_");
                String newKey = tokens2[tokens2.length - 1];

                endpoints.put(newKey, map.get(key).toString());
                counter++;
            }
        }
        logger.info("Saved " + counter + " PATH keys from systemEnvironment");
        logger.info("System Environment PATH keys take precedence over application.properties PATH keys, if present.");

        // log all key/value pairs
        List<MapList> listOfMaps = new ArrayList<>();
        for (Iterator it = ((AbstractEnvironment) this.env).getPropertySources().iterator(); it.hasNext(); ) {
            PropertySource propertySource = (PropertySource) it.next();
            if (propertySource instanceof MapPropertySource) {
                MapList mapList = new MapList();
                map.putAll(((MapPropertySource) propertySource).getSource());
                mapList.setMaps(map);
                mapList.setClassType(propertySource.getName());
                listOfMaps.add(mapList);
            }
        }

        for (MapList mapList : listOfMaps) {

            logger.info("Map [" + mapList.getClassType() + "]");
            Map<String, Object> map2 = mapList.getMaps();
            for (Object key : map2.keySet()) {
                String value = map2.get(key).toString();
                logger.info("\tkey:[" + key + "] value:[" + value + "]");
            }

        }

    }

    public static Map<String, String> getRouteTable() {
        return endpoints;
    }

    private static class MapList {

        public String classType;
        public Map<String, Object> maps;

        public String getClassType() {
            return classType;
        }

        public void setClassType(String classType) {
            this.classType = classType;
        }

        public Map<String, Object> getMaps() {
            return maps;
        }

        public void setMaps(Map<String, Object> maps) {
            this.maps = maps;
        }

    }


}
