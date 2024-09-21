package com.temperature_control.persistence.entity;

import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Entity
@Data

public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    private String message;

    private LocalDateTime alertTime = LocalDateTime.now();

    @ManyToOne
    private TemperatureReading temperatureReading;

}