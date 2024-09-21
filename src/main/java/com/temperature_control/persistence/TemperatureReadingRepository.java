package com.temperature_control.persistence;

import com.temperature_control.persistence.entity.TemperatureReading;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TemperatureReadingRepository extends JpaRepository<TemperatureReading, Integer> {
}