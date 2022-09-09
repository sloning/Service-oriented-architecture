package com.example.soa.controller;

import com.example.soa.model.Coordinates;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/coordinates")
public class CoordinatesController {

    @PostMapping
    public Coordinates create(@RequestBody Coordinates coordinates) {
        return coordinates;
    }

    @GetMapping("/{id}")
    public Coordinates getById(@PathVariable Long id) {
        return new Coordinates();
    }

    @GetMapping
    public List<Coordinates> getAll(Pageable pageable) {
        return new ArrayList<>();
    }

    @PutMapping
    public Coordinates update(@RequestBody Coordinates coordinates) {
        return coordinates;
    }

    @DeleteMapping
    public void delete(@RequestBody Coordinates coordinates) {
    }
}
