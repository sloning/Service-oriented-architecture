package com.example.service.impl;

import com.example.exception.BadRequestException;
import com.example.model.Location;
import com.example.model.Route;
import com.example.model.WithName;
import com.example.service.LocationService;
import com.example.service.NameService;
import com.example.service.RouteService;
import org.springframework.data.domain.Pageable;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import java.util.Comparator;
import java.util.List;

@Stateless(name = "NameService")
@Remote(NameService.class)
public class NameServiceImpl implements NameService {

    @EJB
    private RouteService routeService;

    @EJB
    private LocationService locationService;

    public WithName findMinimumName() {
        List<Route> routes = routeService.findAll();
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
