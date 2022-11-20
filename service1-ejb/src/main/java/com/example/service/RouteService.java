package com.example.service;

import com.example.dto.RouteUpdateRequestDto;
import com.example.dto.RoutesFilterDto;
import com.example.model.Route;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface RouteService {

    public Route getById(int id);

    public Page<Route> findAll(RoutesFilterDto routesFilter, Pageable pageable);

    public List<Route> findAll();

    public Route update(RouteUpdateRequestDto routeUpdateRequestDto);

    public Route save(Route route);

    public void delete(int id);

    public Double computeDistances();

    public Map<String, Long> getGroups();
}
