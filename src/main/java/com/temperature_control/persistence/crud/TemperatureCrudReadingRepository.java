package com.temperature_control.persistence.crud;


import com.temperature_control.persistence.entity.Device;
import com.temperature_control.persistence.entity.TemperatureReading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TemperatureCrudReadingRepository extends JpaRepository<TemperatureReading, Integer> {



    // Buscar lecturas de temperatura por dispositivo
    List<TemperatureReading> findByDevice(Device device);

    // Buscar lecturas de temperatura en un rango de tiempo
    List<TemperatureReading> findByReadingTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    // Buscar lecturas de temperatura superiores a un valor
    List<TemperatureReading> findByTemperatureGreaterThan(Double temperature);

    // Buscar lecturas de temperatura inferiores a un valor
    List<TemperatureReading> findByTemperatureLessThan(Double temperature);

    // Buscar lecturas de temperatura en un rango específico
    List<TemperatureReading> findByTemperatureBetween(Double minTemperature, Double maxTemperature);

    // Buscar lecturas de temperatura ordenadas por tiempo
    List<TemperatureReading> findAllByOrderByReadingTimeAsc();

}
