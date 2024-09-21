package com.temperature_control.persistence.crud;

import com.temperature_control.persistence.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationCrudRepository extends JpaRepository<Location, Integer> {


    // Buscar ubicaciones por nombre
    List<Location> findByName(String name);

    // Buscar ubicaciones por descripción (parcial)
    List<Location> findByDescriptionContaining(String description);

    // Buscar ubicaciones por nombre y descripción
    List<Location> findByNameAndDescription(String name, String description);

    // Buscar ubicaciones ordenadas por nombre
    List<Location> findAllByOrderByNameAsc();
}
