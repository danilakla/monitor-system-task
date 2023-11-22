package com.monitor.monitorservice.repository;

import com.monitor.monitorservice.model.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorTypeRepository extends JpaRepository<SensorType, Integer> {

}