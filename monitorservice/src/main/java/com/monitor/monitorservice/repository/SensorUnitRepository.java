package com.monitor.monitorservice.repository;

import com.monitor.monitorservice.model.SensorUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SensorUnitRepository extends JpaRepository<SensorUnit, Integer> {

}
