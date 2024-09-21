package com.temperature_control.persistence;

import com.temperature_control.persistence.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device, Integer> {
}
