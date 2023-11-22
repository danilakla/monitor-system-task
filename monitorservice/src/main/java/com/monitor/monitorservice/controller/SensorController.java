package com.monitor.monitorservice.controller;

import com.monitor.monitorservice.dto.SensorDTO;
import com.monitor.monitorservice.model.Sensor;
import com.monitor.monitorservice.service.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/sensors")
public class SensorController {

    @Autowired
    private SensorService sensorService;

    @GetMapping
    public ResponseEntity<List<Sensor>> getAllSensors() {
        List<Sensor> sensors = sensorService.getAllSensors();
        return ResponseEntity.ok(sensors);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Sensor> getAllSensors(@PathVariable Integer id) {
        Sensor sensors = sensorService.getSensorById(id);
        return ResponseEntity.ok(sensors);
    }



    @PostMapping
    public ResponseEntity<Sensor> saveSensor(@RequestBody SensorDTO sensor) {
        Sensor savedSensor = sensorService.saveSensor(sensor);
        return ResponseEntity.ok(savedSensor);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Sensor> updateSensor(@RequestBody SensorDTO sensor,@PathVariable Integer id) {
        Sensor updatedSensor = sensorService.updateSensor(sensor,id);
        return ResponseEntity.ok(updatedSensor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable Integer id) {
        sensorService.deleteSensor(id);
        return ResponseEntity.noContent().build();
    }
}