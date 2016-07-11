package com.matsnowak.smartalarm.model;

import javax.persistence.*;

/**
 * Created by Mateusz on 14.06.2016.
 */

@Entity
public class Sensor {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private SensorType sensorType;

    @OneToOne()
    @JoinColumn(name = "slotKey")
    private CommunicationSlot communicationSlot;


    public  Sensor(String name, SensorType sensorType, CommunicationSlot communicationSlot) {
        this.name = name;
        this.sensorType = sensorType;
        this.communicationSlot = communicationSlot;
    }

    protected Sensor() {}

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SensorType getSensorType() {
        return sensorType;
    }

    public void setSensorType(SensorType sensorType) {
        this.sensorType = sensorType;
    }

    public CommunicationSlot getCommunicationSlot() {
        return communicationSlot;
    }

    public void setCommunicationSlot(CommunicationSlot communicationSlot) {
        this.communicationSlot = communicationSlot;
    }
}
