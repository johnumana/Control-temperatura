package com.temperature_control.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import com.temperature_control.persistence.entity.Alert;

public interface AlertRepository extends JpaRepository<Alert, Integer> {
}