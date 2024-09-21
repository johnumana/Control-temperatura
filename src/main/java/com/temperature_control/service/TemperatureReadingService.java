package com.temperature_control.service;

import com.temperature_control.persistence.TemperatureReadingRepository;
import com.temperature_control.persistence.entity.TemperatureReading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TemperatureReadingService {

    private final TemperatureReadingRepository temperatureReadingRepository;

    @Autowired
    public TemperatureReadingService(TemperatureReadingRepository temperatureReadingRepository) {
        this.temperatureReadingRepository = temperatureReadingRepository;
    }

    public TemperatureReading saveTemperatureReading(TemperatureReading temperatureReading) {
        return temperatureReadingRepository.save(temperatureReading);
    }

    public Optional<TemperatureReading> getTemperatureReadingById(Integer id) {
        return temperatureReadingRepository.findById(id);
    }

    public List<TemperatureReading> getAllTemperatureReadings() {
        return temperatureReadingRepository.findAll();
    }

    public void deleteTemperatureReading(Integer id) {
        temperatureReadingRepository.deleteById(id);
    }
}
