package com.example.service2.controller;

import com.example.service2.model.GetOrderedRoutesReq;
import com.example.service2.model.GetOrderedRoutesRes;
import com.example.service2.model.GetRouteByLengthReq;
import com.example.service2.model.GetRouteByLengthRes;
import com.example.service2.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class NavigatorController {
    private static final String NAMESPACE_URI = "https://github.com/sloning/Service-oriented-architecture";

    private final RouteService routeService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getRouteByLengthReq")
    @ResponsePayload
    public GetRouteByLengthRes getRouteByLength(@RequestPayload GetRouteByLengthReq req) {
        var lengthFilterDto = req.getLengthFilterDto();
        var value = routeService.getRouteByLength(lengthFilterDto.getIdFrom(), lengthFilterDto.getIdTo(), lengthFilterDto.getLength());
        var response = new GetRouteByLengthRes();
        response.setRoute(value);
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getOrderedRoutesReq")
    @ResponsePayload
    public GetOrderedRoutesRes getOrderedRoutes(@RequestPayload GetOrderedRoutesReq req) {
        var orderedRoutesDto = req.getOrderedRoutesDto();
        var value = routeService.getOrderedRoutes(orderedRoutesDto.getIdFrom(), orderedRoutesDto.getIdTo(), orderedRoutesDto.getOrderBy());
        var response = new GetOrderedRoutesRes();
        response.setRoutes(value);
        return response;
    }
}
