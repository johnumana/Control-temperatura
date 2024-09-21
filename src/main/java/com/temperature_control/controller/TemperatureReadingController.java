package com.temperature_control.controller;

import com.temperature_control.persistence.entity.TemperatureReading;
import com.temperature_control.service.TemperatureReadingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/temperature-readings")
public class TemperatureReadingController {

    private final TemperatureReadingService temperatureReadingService;

    @Autowired
    public TemperatureReadingController(TemperatureReadingService temperatureReadingService) {
        this.temperatureReadingService = temperatureReadingService;
    }

    @PostMapping
    public ResponseEntity<TemperatureReading> createTemperatureReading(@RequestBody TemperatureReading temperatureReading) {
        if (temperatureReading == null || temperatureReading.getTemperature() == null || temperatureReading.getReadingTime() == null || temperatureReading.getDevice() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        try {
            TemperatureReading savedReading = temperatureReadingService.saveTemperatureReading(temperatureReading);
            return new ResponseEntity<>(savedReading, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemperatureReading> getTemperatureReadingById(@PathVariable Integer id) {
        Optional<TemperatureReading> reading = temperatureReadingService.getTemperatureReadingById(id);
        return reading.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<TemperatureReading>> getAllTemperatureReadings() {
        List<TemperatureReading> readings = temperatureReadingService.getAllTemperatureReadings();
        return ResponseEntity.ok(readings);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TemperatureReading> updateTemperatureReading(@PathVariable Integer id, @RequestBody TemperatureReading temperatureReading) {
        if (temperatureReading == null || temperatureReading.getTemperature() == null || temperatureReading.getReadingTime() == null || temperatureReading.getDevice() == null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        temperatureReading.setId(id);
        try {
            TemperatureReading updatedReading = temperatureReadingService.saveTemperatureReading(temperatureReading);
            return new ResponseEntity<>(updatedReading, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTemperatureReading(@PathVariable Integer id) {
        try {
            temperatureReadingService.deleteTemperatureReading(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
