package com.temperature_control.persistence.crud;

import com.temperature_control.persistence.entity.Alert;
import com.temperature_control.persistence.entity.TemperatureReading;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AlertCrudRepository  extends JpaRepository<Alert, Integer> {

    // Buscar alertas por mensaje
    List<Alert> findByMessageContaining(String message);

    // Buscar alertas por tiempo
    List<Alert> findByAlertTimeBetween(LocalDateTime startTime, LocalDateTime endTime);

    // Buscar alertas por lectura de temperatura
    List<Alert> findByTemperatureReading(TemperatureReading temperatureReading);

    // Buscar alertas por mensaje y tiempo
    List<Alert> findByMessageContainingAndAlertTimeBetween(String message, LocalDateTime startTime, LocalDateTime endTime);

    // Buscar alertas ordenadas por tiempo
    List<Alert> findAllByOrderByAlertTimeAsc();
}
