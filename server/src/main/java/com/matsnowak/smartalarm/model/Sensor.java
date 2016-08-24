package com.matsnowak.smartalarm.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

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

    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "slotKey")
    private Slot slot;


    public  Sensor(String name, SensorType sensorType, Slot slot) {
        this.name = name;
        this.sensorType = sensorType;
        this.slot = slot;
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

    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(getId(), sensor.getId()) &&
                Objects.equals(getName(), sensor.getName()) &&
                getSensorType() == sensor.getSensorType() &&
                Objects.equals(getSlot(), sensor.getSlot());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSensorType(), getSlot());
    }


    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sensorType=" + sensorType +
                ", slot=" + slot +
                '}';
    }
}
