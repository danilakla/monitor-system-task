package com.monitor.monitorservice.controller;

import com.monitor.monitorservice.model.SensorType;
import com.monitor.monitorservice.service.SensorTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/sensor-types")
public class SensorTypeController {

    @Autowired
    private SensorTypeService sensorTypeService;

    @GetMapping
    public ResponseEntity<List<SensorType>> getAllSensorTypes() {
        List<SensorType> sensorTypes = sensorTypeService.getAllSensorTypes();
        return ResponseEntity.ok(sensorTypes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorType> getSensorTypeById(@PathVariable Integer id) {
        return sensorTypeService.getSensorTypeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
