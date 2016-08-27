package com.matsnowak.smartalarm.model;

import javax.persistence.*;
import java.util.*;

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
    private Set<Sensor> sensors = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name="ZONE_SIGNALLER")
    private Set<Signaller> signallers = new LinkedHashSet<>();

    public static Zone create(String name, List<Sensor> sensors, List<Signaller> signallers) {
        Zone zone = new Zone();
        zone.setName(name);
        zone.setSensors(sensors);
        zone.setSignallers(signallers);

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
        return new LinkedList<>(this.sensors); // hack for multiple results
    }

    public void setSensors(List<Sensor> sensors) {
        this.sensors = new LinkedHashSet<>(sensors); // hack for multiple results
    }

    public List<Signaller> getSignallers() {
        return new LinkedList<>(this.signallers); // hack for multiple results
    }

    public void setSignallers(List<Signaller> signallers) {
        this.signallers = new LinkedHashSet<>(signallers); // hack for multiple results
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zone zone = (Zone) o;
        return Objects.equals(getId(), zone.getId()) &&
                Objects.equals(getName(), zone.getName()) &&
                Objects.equals(getSensors(), zone.getSensors()) &&
                Objects.equals(getSignallers(), zone.getSignallers());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSensors(), getSignallers());
    }

    @Override
    public String toString() {
        return "Zone{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sensors=" + sensors +
                ", signallers=" + signallers +
                '}';
    }
}
