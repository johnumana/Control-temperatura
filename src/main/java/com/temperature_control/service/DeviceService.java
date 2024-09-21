package com.temperature_control.service;

import com.temperature_control.persistence.DeviceRepository;
import com.temperature_control.persistence.entity.Device;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepository;

    @Autowired
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    public Device saveDevice(Device device) {
        return deviceRepository.save(device);
    }

    public Optional<Device> getDeviceById(Integer id) {
        return deviceRepository.findById(id);
    }

    public List<Device> getAllDevices() {
        return deviceRepository.findAll();
    }

    public Device updateDevice(Device device) {
        // Se asume que el dispositivo existe antes de actualizar
        return deviceRepository.save(device);
    }

    public void deleteDevice(Integer id) {
        deviceRepository.deleteById(id);
    }
}
