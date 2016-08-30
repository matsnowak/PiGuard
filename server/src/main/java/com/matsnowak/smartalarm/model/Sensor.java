package com.matsnowak.smartalarm.model;

import ch.qos.logback.core.rolling.TriggeringPolicy;

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
    private TriggeringType triggeredOn;

    @Column(nullable = false)
    private PullResistance pullResistance = PullResistance.OFF;


    @NotNull
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "slotKey")
    private Slot slot;


    public  Sensor(String name, Slot slot, TriggeringType triggeredOn, PullResistance pullResistance) {
        this.name = name;
        this.slot = slot;
        this.triggeredOn = triggeredOn;
        if (pullResistance != null) {
            this.pullResistance = pullResistance;
        }
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


    public Slot getSlot() {
        return slot;
    }

    public void setSlot(Slot slot) {
        this.slot = slot;
    }

    public TriggeringType getTriggeredOn() {
        return triggeredOn;
    }

    public void setTriggeredOn(TriggeringType triggeredOn) {
        this.triggeredOn = triggeredOn;
    }

    public PullResistance getPullResistance() {
        return pullResistance;
    }

    public void setPullResistance(PullResistance pullResistance) {
        this.pullResistance = pullResistance;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", triggeredOn=" + triggeredOn +
                ", pullResistance=" + pullResistance +
                ", slot=" + slot +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sensor sensor = (Sensor) o;
        return Objects.equals(getId(), sensor.getId()) &&
                Objects.equals(getName(), sensor.getName()) &&
                getTriggeredOn() == sensor.getTriggeredOn() &&
                getPullResistance() == sensor.getPullResistance() &&
                Objects.equals(getSlot(), sensor.getSlot());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getTriggeredOn(), getPullResistance(), getSlot());
    }
}
