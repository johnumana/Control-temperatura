package com.temperature_control.persistence.crud;

import com.temperature_control.persistence.entity.Device;
import com.temperature_control.persistence.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DeviceCrudRepository extends JpaRepository<Device, Integer> {
    // Buscar dispositivos por nombre
    List<Device> findByName(String name);

    // Buscar dispositivos por ubicación
    List<Device> findByLocation(Location location);

    // Buscar dispositivos por nombre y ubicación
    List<Device> findByNameAndLocation(String name, Location location);

    // Buscar dispositivos por parte de su nombre
    List<Device> findByNameContaining(String namePart);

    // Buscar dispositivos ordenados por nombre
    List<Device> findAllByOrderByNameAsc();
}
