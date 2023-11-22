package com.monitor.monitorservice.service;

import com.monitor.monitorservice.model.SensorType;

import java.util.List;
import java.util.Optional;

public interface SensorTypeService {

     List<SensorType> getAllSensorTypes();

     Optional<SensorType> getSensorTypeById(Integer id);
}
