package com.monitor.monitorservice.service;

import com.monitor.monitorservice.model.SensorUnit;

import java.util.List;
import java.util.Optional;

public interface SensorUnitService {

     List<SensorUnit> getAllSensorUnits();

     Optional<SensorUnit> getSensorUnitById(Integer id);
}
