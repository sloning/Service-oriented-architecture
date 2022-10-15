package com.example.service1.service;

import com.example.service1.dto.RoutesFilterDto;
import com.example.service1.exception.BadRequestException;
import com.example.service1.model.Location;
import com.example.service1.model.Route;
import com.example.service1.model.WithName;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class NameService {

    private final RouteService routeService;
    private final LocationService locationService;

    public WithName findMinimumName() {
        List<Route> routes = routeService.findAll(new RoutesFilterDto(), Pageable.unpaged());
        List<Location> locations = locationService.findAll(Pageable.unpaged());

        if (routes.isEmpty() && locations.isEmpty()) {
            throw new BadRequestException("There are no names");
        }

        Route routeWithMinimumName = routes.stream().min(Comparator.comparingInt(route -> route.getName().length())).orElse(null);
        Location locationWithMinimumName = locations.stream().min(Comparator.comparingInt(location -> location.getName().length())).orElse(null);
        if (routeWithMinimumName != null && locationWithMinimumName == null) {
            return routeWithMinimumName;
        }
        if (routeWithMinimumName == null) {
            return locationWithMinimumName;
        }
        if (routeWithMinimumName.getName().length() <= locationWithMinimumName.getName().length()) {
            return routeWithMinimumName;
        }
        return locationWithMinimumName;
    }
}
