package com.example.service;

import com.example.model.Location;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface LocationService {

    public Location getById(int id);

    public Optional<Location> findById(int id);

    public List<Location> findAll(Pageable pageable);

    public Location save(Location location);

    public void delete(int id);
}
