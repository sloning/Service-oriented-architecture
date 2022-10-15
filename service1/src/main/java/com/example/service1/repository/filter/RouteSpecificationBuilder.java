package com.example.service1.repository.filter;

import com.example.service1.model.Route;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class RouteSpecificationBuilder {

    private final List<SearchCriteria> params;

    public RouteSpecificationBuilder() {
        params = new ArrayList<>();
    }

    public void add(final String key, final SearchOperation searchOperation, final Object value) {
        SearchCriteria searchCriteria = new SearchCriteria(key, searchOperation, value);
        params.add(searchCriteria);
    }

    public void add(final List<String> key, final SearchOperation searchOperation, final Object value) {
        SearchCriteria searchCriteria = new SearchCriteria(key, searchOperation, value);
        params.add(searchCriteria);
    }

    public Specification<Route> build() {
        if (params.isEmpty()) {
            return null;
        }

        List<RouteSpecification> specs = params.stream()
                .map(RouteSpecification::new)
                .toList();

        Specification<Route> result = specs.get(0);
        for (RouteSpecification routeSpecification : specs) {
            result = Specification.where(result).and(routeSpecification);
        }
        return result;
    }
}
