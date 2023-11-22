package com.monitor.monitorservice.service.impl;

import com.monitor.monitorservice.model.SensorUnit;
import com.monitor.monitorservice.repository.SensorUnitRepository;
import com.monitor.monitorservice.service.SensorUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SensorUnitServiceImpl implements SensorUnitService {

    @Autowired
    private SensorUnitRepository sensorUnitRepository;

    public List<SensorUnit> getAllSensorUnits() {
        return sensorUnitRepository.findAll();
    }

    public Optional<SensorUnit> getSensorUnitById(Integer id) {
        return sensorUnitRepository.findById(id);
    }
}
