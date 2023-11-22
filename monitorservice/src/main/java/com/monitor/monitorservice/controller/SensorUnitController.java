package com.monitor.monitorservice.controller;

import com.monitor.monitorservice.model.SensorUnit;
import com.monitor.monitorservice.service.SensorUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/sensor-units")
public class SensorUnitController {

    @Autowired
    private SensorUnitService sensorUnitService;

    @GetMapping
    public ResponseEntity<List<SensorUnit>> getAllSensorUnits() {
        List<SensorUnit> sensorUnits = sensorUnitService.getAllSensorUnits();
        return ResponseEntity.ok(sensorUnits);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorUnit> getSensorUnitById(@PathVariable Integer id) {
        return sensorUnitService.getSensorUnitById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}