package com.example.proxy.service;

import com.example.proxy.model.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.net.ssl.HttpsURLConnection;
import java.util.List;

public class RouteService extends WebServiceGatewaySupport {

    static {
        System.setProperty("javax.net.ssl.trustStore", RouteService.class.getClassLoader().getResource("soa.jks").getFile());
        HttpsURLConnection.setDefaultHostnameVerifier ((hostname, session) -> true);
    }

    public Route getRouteByLength(LengthFilterDto lengthFilterDto) {
        var req = new GetRouteByLengthReq();
        req.setLengthFilterDto(lengthFilterDto);

        var response = (GetRouteByLengthRes) getWebServiceTemplate().marshalSendAndReceive(req);
        return response.getRoute();
    }

    public List<Route> getOrderedRoutes(OrderedRoutesDto orderedRoutesDto) {
        var req = new GetOrderedRoutesReq();
        req.setOrderedRoutesDto(orderedRoutesDto);

        var response = (GetOrderedRoutesRes) getWebServiceTemplate().marshalSendAndReceive(req);
        return response.getRoutes();
    }
}
