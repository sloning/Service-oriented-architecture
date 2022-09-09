package com.example.soa.controller;

import com.example.soa.model.Route;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/routes")
public class RouteController {

    @PostMapping
    public Route create(@RequestBody Route route) {
        return route;
    }

    @GetMapping("/{id}")
    public Route getById(@PathVariable Long id) {
        return new Route();
    }

    @GetMapping
    public List<Route> getAll(Pageable pageable) {
        return new ArrayList<>();
    }

    @PutMapping
    public Route update(@RequestBody Route route) {
        return route;
    }

    @DeleteMapping
    public void delete(@RequestBody Route route) {

    }
}
