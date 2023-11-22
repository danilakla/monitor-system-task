package com.monitor.monitorservice.service.impl;

import com.monitor.monitorservice.model.SensorType;
import com.monitor.monitorservice.repository.SensorTypeRepository;
import com.monitor.monitorservice.service.SensorTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorTypeServiceImpl implements SensorTypeService {

    @Autowired
    private SensorTypeRepository sensorTypeRepository;

    public List<SensorType> getAllSensorTypes() {
        return sensorTypeRepository.findAll();
    }

    public Optional<SensorType> getSensorTypeById(Integer id) {
        return sensorTypeRepository.findById(id);
    }
}