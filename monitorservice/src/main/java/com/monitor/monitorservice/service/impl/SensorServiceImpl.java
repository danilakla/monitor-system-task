package com.monitor.monitorservice.service.impl;

import com.monitor.monitorservice.dto.SensorDTO;
import com.monitor.monitorservice.model.Sensor;
import com.monitor.monitorservice.model.SensorType;
import com.monitor.monitorservice.model.SensorUnit;
import com.monitor.monitorservice.repository.SensorRepository;
import com.monitor.monitorservice.service.SensorService;
import com.monitor.monitorservice.service.SensorTypeService;
import com.monitor.monitorservice.service.SensorUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;

@Service
public class SensorServiceImpl implements SensorService {

    @Autowired
    private SensorRepository sensorRepository;
    @Autowired
    private SensorUnitService sensorUnitService;
    @Autowired
    SensorTypeService sensorTypeService;

    public List<Sensor> getAllSensors() {
        return sensorRepository.findAll();
    }

    public Sensor getSensorById(Integer id) {
        return sensorRepository.findById(id).get();
    }

    public Sensor updateSensor(SensorDTO sensorDTO, Integer id) {
        Sensor sensor=getSensorById(id);
        if(sensor ==null) throw new HttpClientErrorException(HttpStatus.BAD_REQUEST);
        Sensor updatedSensor= mapSensorDtoToSensorEntity(sensorDTO);
        updatedSensor.setId(id);
        return sensorRepository.save(updatedSensor);
    }

    public Sensor saveSensor(SensorDTO  sensor) {
        return sensorRepository.save(mapSensorDtoToSensorEntity(sensor));
    }

    public void deleteSensor(Integer id) {
        sensorRepository.deleteById(id);
    }

    private Sensor mapSensorDtoToSensorEntity(SensorDTO sensorDTO){
            Sensor sensor= new Sensor();

            sensor.setDescription(sensorDTO.getDescription());
            sensor.setLocation(sensorDTO.getLocation());
            sensor.setModel(sensorDTO.getModel());
            sensor.setRangeFrom(sensorDTO.getRangeFrom());
            sensor.setRangeTo(sensorDTO.getRangeTo());
            sensor.setName(sensorDTO.getName());
            SensorUnit sensorUnit=sensorUnitService.getSensorUnitById(sensorDTO.getUnitId()).get();
            SensorType sensorType=sensorTypeService.getSensorTypeById(sensorDTO.getTypeId()).get();
            sensor.setUnit(sensorUnit);
            sensor.setType(sensorType);
        return sensor;
    }
}
