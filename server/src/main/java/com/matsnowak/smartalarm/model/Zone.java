package com.matsnowak.smartalarm.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mateusz on 14.06.2016.
 */
@Entity
public class Zone {

    @Id
    @GeneratedValue
    Integer id;

    @Column(nullable = false)
    private String name;

    @ManyToMany()
    @JoinTable(name="ZONE_SENSOR")
    private List<Sensor> sensors = new LinkedList<Sensor>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Sensor> getSensors() {
        return sensors;
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = sensors;
    }
}
