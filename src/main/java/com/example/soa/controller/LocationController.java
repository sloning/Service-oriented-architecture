package com.example.soa.controller;

import com.example.soa.model.Location;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {

    @PostMapping
    public Location create(@RequestBody Location location) {
        return location;
    }

    @GetMapping("/{id}")
    public Location getById(@PathVariable Long id) {
        return new Location();
    }

    @GetMapping
    public List<Location> getAll(Pageable pageable) {
        return new ArrayList<>();
    }

    @PutMapping
    public Location update(@RequestBody Location location) {
        return location;
    }

    @DeleteMapping
    public void delete(@RequestBody Location location) {

    }
}
