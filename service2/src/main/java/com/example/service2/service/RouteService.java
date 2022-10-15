package com.example.service2.service;

import com.example.service2.exception.BadRequestException;
import com.example.service2.exception.EntityNotFoundException;
import com.example.service2.model.Length;
import com.example.service2.model.Route;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@Service
public class RouteService {
    static {
        System.setProperty("javax.net.ssl.trustStore", RouteService.class.getClassLoader().getResource("soa.jks").getFile());
        System.setProperty("javax.net.ssl.trustStorePassword", "helios");
        System.setProperty("javax.net.ssl.keyStore", RouteService.class.getClassLoader().getResource("soa.jks").getFile());
        System.setProperty("javax.net.ssl.keyStorePassword", "helios");

        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                (hostname, sslSession) -> hostname.equals("localhost")
        );
    }

    private final RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper().registerModule(new JavaTimeModule());

    private static final String ROUTES_URL = "https://localhost:31000/routes";

    public List<Route> findFromLocationToLocation(Integer idFrom, Integer idTo, Integer page, Integer size, String orderBy) {
        String url = buildUrl(idFrom, idTo, page, size, orderBy);
        List<Map<String, Object>> routesList = new ArrayList<>();
        try {
            routesList = restTemplate.getForObject(url, List.class);
        } catch (Exception e) {
            throw new BadRequestException("Invalid request");
        }

        List<Route> routes = new ArrayList<>();
        for (var routeMap : routesList) {
            routes.add(mapper.convertValue(routeMap, Route.class));
        }

        return routes;
    }

    private String buildUrl(Integer idFrom, Integer idTo, Integer page, Integer size, String orderBy) {
        String url = ROUTES_URL;
        url += "?locationFromFilter=" + idFrom;
        url += "&locationToFilter=" + idTo;
        url += "&page=";
        url += page == null ? 0 : page;
        url += "&size=";
        url += size == null ? Integer.MAX_VALUE : size;
        if (orderBy != null) {
            url += "&sort=" + orderBy + ",desc";
        }
        return url;
    }

    public List<Route> findFromLocationToLocation(Integer idFrom, Integer idTo) {
        return findFromLocationToLocation(idFrom, idTo, 0, Integer.MAX_VALUE, null);
    }

    public Route getRouteByLength(Integer idFrom, Integer idTo, Length length) {
        if (length == null) {
            throw new BadRequestException("Invalid length");
        }
        List<Route> routes = findFromLocationToLocation(idFrom, idTo);
        if (routes.isEmpty()) {
            throw new EntityNotFoundException("There are no routes");
        }

        if (length == Length.SHORTEST) {
            return routes.stream().min(Comparator.comparing(route -> route.getDistance())).get();
        }
        return routes.stream().max(Comparator.comparing(route -> route.getDistance())).get();
    }

    public List<Route> getOrderedRoutes(Integer idFrom, Integer idTo, String orderBy) {
        return findFromLocationToLocation(idFrom, idTo, 0, 1, orderBy);
    }
}
