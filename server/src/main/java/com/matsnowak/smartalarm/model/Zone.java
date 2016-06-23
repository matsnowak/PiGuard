package com.matsnowak.smartalarm.model;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Mateusz on 14.06.2016.
 */
public class Zone {
    private String name;
    private List<Sensor> sensors = new LinkedList<Sensor>();

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
