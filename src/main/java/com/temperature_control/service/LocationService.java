package com.temperature_control.service;

import com.temperature_control.persistence.LocationRepository;
import com.temperature_control.persistence.entity.Location;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    // Método para obtener una ubicación por su ID
    public Optional<Location> getLocationById(Integer id) {
        return locationRepository.findById(id);
    }

    // Método para guardar una nueva ubicación (si es necesario)
    public Location saveLocation(Location location) {
        return locationRepository.save(location);
    }

    // Método para obtener todas las ubicaciones (si es necesario)
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    // Método para actualizar una ubicación (si es necesario)
    public Location updateLocation(Location location) {
        return locationRepository.save(location);
    }

    // Método para eliminar una ubicación por su ID (si es necesario)
    public void deleteLocation(Integer id) {
        locationRepository.deleteById(id);
    }


}
