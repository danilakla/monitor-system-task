package com.monitor.monitorservice.service;

import com.monitor.monitorservice.dto.SensorDTO;
import com.monitor.monitorservice.model.Sensor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface SensorService {

     List<Sensor> getAllSensors( );


     Sensor updateSensor(SensorDTO sensorUpdated, Integer id);

     Sensor saveSensor(SensorDTO sensor) ;
     Sensor getSensorById(Integer id) ;

     void deleteSensor(Integer id) ;
}
