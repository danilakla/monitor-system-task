package com.monitor.monitorservice.model;

import javax.persistence.*;

@Entity
@Table(name = "sensor")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer  id;

    @Column(name = "name", nullable = false, length = 30)
    private String name;

    @Column(name = "model", nullable = false, length = 15)
    private String model;

    @Column(name = "range_from")
    private Integer rangeFrom;

    @Column(name = "range_to")
    private Integer rangeTo;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private SensorType type;

    @ManyToOne
    @JoinColumn(name = "unit_id")
    private SensorUnit unit;

    @Column(name = "location", length = 40)
    private String location;

    @Column(name = "description", length = 200)
    private String description;

    public Integer  getId() {
        return id;
    }

    public void setId(Integer  id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getRangeFrom() {
        return rangeFrom;
    }

    public void setRangeFrom(Integer rangeFrom) {
        this.rangeFrom = rangeFrom;
    }

    public Integer getRangeTo() {
        return rangeTo;
    }

    public void setRangeTo(Integer rangeTo) {
        this.rangeTo = rangeTo;
    }

    public SensorType getType() {
        return type;
    }

    public void setType(SensorType type) {
        this.type = type;
    }

    public SensorUnit getUnit() {
        return unit;
    }

    public void setUnit(SensorUnit unit) {
        this.unit = unit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
