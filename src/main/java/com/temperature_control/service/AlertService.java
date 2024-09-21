package com.temperature_control.service;


import com.temperature_control.persistence.crud.AlertCrudRepository;
import com.temperature_control.persistence.entity.Alert;
import com.temperature_control.persistence.entity.TemperatureReading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AlertService {

    @Autowired
    private AlertCrudRepository alertCrudRepository;

    public void save(Alert alert) {
        alertCrudRepository.save(alert);
    }

    public Optional<Alert> findById(Integer id) {
        return alertCrudRepository.findById(id);
    }

    public List<Alert> findAll() {
        return alertCrudRepository.findAllByOrderByAlertTimeAsc();
    }

    public void delete(Alert alert) {
        alertCrudRepository.delete(alert);
    }

    public List<Alert> findByMessage(String message) {
        return alertCrudRepository.findByMessageContaining(message);
    }

    public List<Alert> findByAlertTimeRange(LocalDateTime startTime, LocalDateTime endTime) {
        return alertCrudRepository.findByAlertTimeBetween(startTime, endTime);
    }

    public List<Alert> findByTemperatureReading(TemperatureReading temperatureReading) {
        return alertCrudRepository.findByTemperatureReading(temperatureReading);
    }

    public List<Alert> findByMessageAndAlertTimeRange(String message, LocalDateTime startTime, LocalDateTime endTime) {
        return alertCrudRepository.findByMessageContainingAndAlertTimeBetween(message, startTime, endTime);
    }
}
