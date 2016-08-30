package com.matsnowak.smartalarm.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Created by Mateusz Nowak on 24.08.2016.
 */
@Entity
public class ArmedZone {
    @Id
    @GeneratedValue
    private Integer id;

    @OneToOne
    @JoinColumn(name = "zoneId")
    private Zone zone;

    @Column(nullable = false)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime startGuardFrom;

    public ArmedZone() {}

    public Zone getZone() {
        return zone;
    }


    public LocalDateTime getStartGuardFrom() {
        return startGuardFrom;
    }

    public static ArmedZone create(Zone zone, LocalDateTime startMonitoringFrom) {
        return new ArmedZone(zone, startMonitoringFrom);
    }

    private ArmedZone(Zone zone, LocalDateTime startGuardFrom) {
        this.zone = zone;
        this.startGuardFrom = startGuardFrom;
    }

    @Override
    public String toString() {
        return "ArmedZone{" +
                "id=" + id +
                ", zone=" + zone +
                ", startGuardFrom=" + startGuardFrom +
                '}';
    }
}
