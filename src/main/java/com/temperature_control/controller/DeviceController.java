package com.temperature_control.controller;

import com.temperature_control.persistence.entity.Device;
import com.temperature_control.persistence.entity.Location;
import com.temperature_control.service.DeviceService;
import com.temperature_control.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/devices")
public class DeviceController {

    private final DeviceService deviceService;
    private final LocationService locationService;

    @Autowired
    public DeviceController(DeviceService deviceService, LocationService locationService) {
        this.deviceService = deviceService;
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<?> createDevice(@RequestBody Device device) {
        if (device == null) {
            return new ResponseEntity<>("Device cannot be null", HttpStatus.BAD_REQUEST);
        }

        // Verificar y asignar Location si es necesario
        if (device.getLocation() != null && device.getLocation().getIdLocation() != null) {
            Optional<Location> location = locationService.getLocationById(device.getLocation().getIdLocation());
            if (location.isEmpty()) {
                return new ResponseEntity<>("Location not found", HttpStatus.BAD_REQUEST);
            }
            device.setLocation(location.get());
        } else {
            // Asumiendo que Location ID es obligatorio
            return new ResponseEntity<>("Location ID is required", HttpStatus.BAD_REQUEST);
        }

        try {
            Device savedDevice = deviceService.saveDevice(device);
            return new ResponseEntity<>(savedDevice, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Device> getDeviceById(@PathVariable Integer id) {
        Optional<Device> device = deviceService.getDeviceById(id);
        return device.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<Device>> getAllDevices() {
        List<Device> devices = deviceService.getAllDevices();
        return ResponseEntity.ok(devices);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDevice(@PathVariable Integer id, @RequestBody Device device) {
        if (device == null) {
            return new ResponseEntity<>("Device cannot be null", HttpStatus.BAD_REQUEST);
        }

        // Verificar y asignar Location si es necesario
        if (device.getLocation() != null && device.getLocation().getIdLocation() != null) {
            Optional<Location> location = locationService.getLocationById(device.getLocation().getIdLocation());
            if (location.isEmpty()) {
                return new ResponseEntity<>("Location not found", HttpStatus.BAD_REQUEST);
            }
            device.setLocation(location.get());
        } else {
            // Asumiendo que Location ID es obligatorio
            return new ResponseEntity<>("Location ID is required", HttpStatus.BAD_REQUEST);
        }

        device.setId(id);
        try {
            Device updatedDevice = deviceService.updateDevice(device);
            return new ResponseEntity<>(updatedDevice, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDevice(@PathVariable Integer id) {
        try {
            deviceService.deleteDevice(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
