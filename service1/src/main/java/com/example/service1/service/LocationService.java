package com.example.service1.service;

import com.example.service1.exception.EntityNotFoundException;
import com.example.service1.model.Location;
import com.example.service1.repository.LocationRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LocationService {

    private final LocationRepository locationRepository;

    public Location getById(int id) {
        return locationRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Location was not found"));
    }

    public Optional<Location> findById(int id) {
        return locationRepository.findById(id);
    }

    public List<Location> findAll(Pageable pageable) {
        return locationRepository.findAll(pageable).toList();
    }

    public Location save(Location location) {
        return locationRepository.save(location);
    }

    public void delete(int id) {
        locationRepository.deleteById(id);
    }
}
