package com.example.service1.service;

import com.example.service1.dto.RoutesFilterDto;
import com.example.service1.exception.BadRequestException;
import com.example.service1.exception.EntityNotFoundException;
import com.example.service1.model.Location;
import com.example.service1.model.Route;
import com.example.service1.repository.RouteRepository;
import com.example.service1.repository.filter.RouteSpecificationBuilder;
import com.example.service1.repository.filter.SearchOperation;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class RouteService {

    private final RouteRepository routeRepository;
    private final LocationService locationService;

    public Route getById(int id) {
        return routeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Route was not found"));
    }

    public List<Route> findAll(RoutesFilterDto routesFilter, Pageable pageable) {
        Specification<Route> spec = getSpecification(routesFilter);
        return routeRepository.findAll(spec, pageable).toList();
    }

    public List<Route> findAll() {
        return findAll(new RoutesFilterDto(), Pageable.unpaged());
    }

    private Specification<Route> getSpecification(RoutesFilterDto routesFilter) {
        RouteSpecificationBuilder specBuilder = new RouteSpecificationBuilder();
        if (routesFilter.getNameFilter() != null) {
            specBuilder.add("name", SearchOperation.LIKE, routesFilter.getNameFilter());
        }
        if (routesFilter.getCoordinatesXFilter() != null) {
            specBuilder.add(List.of("coordinates", "x"), SearchOperation.EQUALITY, routesFilter.getCoordinatesXFilter());
        }
        if (routesFilter.getCoordinatesYFilter() != null) {
            specBuilder.add(List.of("coordinates", "y"), SearchOperation.EQUALITY, routesFilter.getCoordinatesYFilter());
        }
        if (routesFilter.getCreationDateFilter() != null) {
            specBuilder.add("creation_date", SearchOperation.EQUALITY, routesFilter.getCreationDateFilter());
        }
        if (routesFilter.getLocationFromFilter() != null) {
            specBuilder.add(List.of("from", "id"), SearchOperation.EQUALITY, routesFilter.getLocationFromFilter());
        }
        if (routesFilter.getLocationToFilter() != null) {
            specBuilder.add(List.of("to", "id"), SearchOperation.EQUALITY, routesFilter.getLocationToFilter());
        }
        if (routesFilter.getDistanceFilter() != null) {
            specBuilder.add("distance", SearchOperation.EQUALITY, routesFilter.getDistanceFilter());
        }
        return specBuilder.build();
    }

    public Route update(Route route) {
        if (route.getId() == null) {
            throw new BadRequestException("Invalid data");
        }

        getById(route.getId());
        return save(route);
    }

    public Route save(Route route) {
        route.setFrom(handleLocation(route.getFrom()));
        route.setTo(handleLocation(route.getTo()));

        return routeRepository.save(route);
    }

    private Location handleLocation(Location location) {
        if (location.getId() == null) {
            return locationService.save(location);
        }
        locationService.getById(location.getId());
        return location;
    }

    public void delete(int id) {
        getById(id);
        routeRepository.deleteById(id);
    }

    public Double computeDistances() {
        return findAll().stream()
                .mapToDouble(Route::getDistance)
                .sum();
    }

    public Map<String, Long> getGroups() {
        Map<String, Long> result = new HashMap<>();
        List<Route> routes = findAll();
        for (Route route : routes) {
            String key = route.getTo().getName();
            Long usage = result.computeIfAbsent(key, k -> 0L);
            usage++;
            result.put(key, usage);
        }
        return result;
    }
}
