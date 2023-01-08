package com.example.service2.service;

import com.example.service2.exception.BadRequestException;
import com.example.service2.exception.EntityNotFoundException;
import com.example.service2.model.GetRoutesReq;
import com.example.service2.model.GetRoutesRes;
import com.example.service2.model.Length;
import com.example.service2.model.Route;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.util.Comparator;
import java.util.List;

public class RouteService extends WebServiceGatewaySupport {
    static {
        System.setProperty("javax.net.ssl.trustStore", RouteService.class.getClassLoader().getResource("soa.jks").getFile());
        System.setProperty("javax.net.ssl.trustStorePassword", "helios");

        javax.net.ssl.HttpsURLConnection.setDefaultHostnameVerifier(
                (hostname, sslSession) -> hostname.equals("localhost")
        );
    }

    public List<Route> findFromLocationToLocation(Integer idFrom, Integer idTo, Integer page, Integer size, String orderBy) {
        var req = new GetRoutesReq();
        req.setIdFrom(idFrom);
        req.setIdTo(idTo);
        req.setPage(page);
        req.setSize(size);
        if (orderBy != null) {
            String sort = orderBy + ", desc";
            req.setSort(sort);
        }

        var res = (GetRoutesRes) getWebServiceTemplate().marshalSendAndReceive(req);
        return res.getRoutes();
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
        return findFromLocationToLocation(idFrom, idTo, 0, Integer.MAX_VALUE, orderBy);
    }
}
