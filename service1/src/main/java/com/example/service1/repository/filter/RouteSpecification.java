package com.example.service1.repository.filter;

import com.example.service1.model.Route;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.List;

public class RouteSpecification implements Specification<Route> {

    private final SearchCriteria searchCriteria;

    public RouteSpecification(final SearchCriteria searchCriteria) {
        super();
        this.searchCriteria = searchCriteria;
    }

    @Override
    public Predicate toPredicate(Root<Route> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
        switch (searchCriteria.getSearchOperation()) {
            case EQUALITY -> {
                return criteriaBuilder.equal(getPath(root, searchCriteria.getKey()), searchCriteria.getValue());
            }
            case LIKE -> {
                return criteriaBuilder.like(root.get(searchCriteria.getKey().get(0)), "%" + searchCriteria.getValue() + "%");
            }
        }
        return null;
    }

    private Path<Route> getPath(Root<Route> root, List<String> key) {
        Path<Route> path = root;
        for (String subKey : key) {
            path = path.get(subKey);
        }
        return path;
    }
}
