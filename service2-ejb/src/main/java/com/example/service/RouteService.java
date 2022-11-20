package com.example.service;

import com.example.model.Length;
import com.example.model.Route;

import java.util.List;

public interface RouteService {

    Route getRouteByLength(Integer idFrom, Integer idTo, Length length);

    List<Route> getOrderedRoutes(Integer idFrom, Integer idTo, String orderBy);
}
