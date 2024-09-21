package com.temperature_control.controller;

import com.temperature_control.persistence.crud.AlertCrudRepository;
import com.temperature_control.persistence.crud.TemperatureCrudReadingRepository;
import com.temperature_control.persistence.entity.Alert;
import com.temperature_control.persistence.entity.TemperatureReading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/alerts")
public class AlertController {

    @Autowired
    private AlertCrudRepository alertCrudRepository;

    @Autowired
    private TemperatureCrudReadingRepository temperatureReadingRepository;

    // Obtener todas las alertas
    @GetMapping
    public ResponseEntity<List<Alert>> getAllAlerts() {
        List<Alert> alerts = alertCrudRepository.findAllByOrderByAlertTimeAsc();
        return ResponseEntity.ok(alerts);
    }

    // Obtener una alerta por ID
    @GetMapping("/{id}")
    public ResponseEntity<Alert> getAlertById(@PathVariable Integer id) {
        Optional<Alert> alert = alertCrudRepository.findById(id);
        return alert.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear una nueva alerta
    @PostMapping
    public ResponseEntity<Alert> createAlert(@RequestBody Alert alert) {
        Alert savedAlert = alertCrudRepository.save(alert);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAlert);
    }

    // Actualizar una alerta existente
    @PutMapping("/{id}")
    public ResponseEntity<Alert> updateAlert(@PathVariable Integer id, @RequestBody Alert alert) {
        if (!alertCrudRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        alert.setId(id);
        Alert updatedAlert = alertCrudRepository.save(alert);
        return ResponseEntity.ok(updatedAlert);
    }

    // Eliminar una alerta por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAlert(@PathVariable Integer id) {
        if (!alertCrudRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        alertCrudRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // Buscar alertas por mensaje
    @GetMapping("/search/message")
    public ResponseEntity<List<Alert>> getAlertsByMessage(@RequestParam String message) {
        List<Alert> alerts = alertCrudRepository.findByMessageContaining(message);
        return ResponseEntity.ok(alerts);
    }

    // Buscar alertas por rango de tiempo
    @GetMapping("/search/time")
    public ResponseEntity<List<Alert>> getAlertsByTimeRange(
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime) {
        List<Alert> alerts = alertCrudRepository.findByAlertTimeBetween(startTime, endTime);
        return ResponseEntity.ok(alerts);
    }

    // Buscar alertas por lectura de temperatura
    @GetMapping("/search/temperature")
    public ResponseEntity<List<Alert>> getAlertsByTemperatureReading(@RequestParam Integer temperatureReadingId) {
        Optional<TemperatureReading> temperatureReadingOpt = temperatureReadingRepository.findById(temperatureReadingId);
        if (temperatureReadingOpt.isPresent()) {
            List<Alert> alerts = alertCrudRepository.findByTemperatureReading(temperatureReadingOpt.get());
            return ResponseEntity.ok(alerts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Buscar alertas por mensaje y rango de tiempo
    @GetMapping("/search")
    public ResponseEntity<List<Alert>> getAlertsByMessageAndTimeRange(
            @RequestParam String message,
            @RequestParam LocalDateTime startTime,
            @RequestParam LocalDateTime endTime) {
        List<Alert> alerts = alertCrudRepository.findByMessageContainingAndAlertTimeBetween(message, startTime, endTime);
        return ResponseEntity.ok(alerts);
    }
}
