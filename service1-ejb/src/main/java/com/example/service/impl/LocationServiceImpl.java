package com.example.service.impl;

import com.example.exception.EntityNotFoundException;
import com.example.model.Location;
import com.example.repository.LocationRepository;
import com.example.service.LocationService;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.annotation.PostConstruct;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

@Stateless(name = "LocationService")
@Remote(LocationService.class)
public class LocationServiceImpl implements LocationService {

    @Inject
    private LocationRepository locationRepository;

    @PersistenceContext(unitName = "soa")
    private EntityManager em;

    @PostConstruct
    public void init() {
        RepositoryFactorySupport factory = new JpaRepositoryFactory(em);
        locationRepository = factory.getRepository(LocationRepository.class);
    }

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
