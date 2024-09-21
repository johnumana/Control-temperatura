package com.temperature_control.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;



@Entity
@Data


public class TemperatureReading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private Double temperature;


    private LocalDateTime readingTime;

    @ManyToOne
    @JoinColumn(name = "device_id", nullable = false)
    private Device device;


}