package com.matsnowak.smartalarm.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

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

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="ZONE_SENSOR")
    private List<Sensor> sensors = new LinkedList<Sensor>();

    public static Zone create(String name, List<Sensor> sensors) {
        Zone zone = new Zone();
        zone.setName(name);
        zone.setSensors(sensors);

        return zone;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zone zone = (Zone) o;
        return Objects.equals(getId(), zone.getId()) &&
                Objects.equals(getName(), zone.getName()) &&
                Objects.equals(getSensors(), zone.getSensors());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSensors());
    }

    @Override
    public String toString() {
        return "Zone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sensors=" + sensors +
                '}';
    }
}
