package com.matsnowak.smartalarm.model;

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
    @PrimaryKeyJoinColumn
    private Zone zone;

    @Column(nullable = false)
    private LocalDateTime startMonitoringFrom;

    public ArmedZone() {}

    public Zone getZone() {
        return zone;
    }


    public LocalDateTime getStartMonitoringFrom() {
        return startMonitoringFrom;
    }

    public static ArmedZone create(Zone zone, LocalDateTime startMonitoringFrom) {
        return new ArmedZone(zone, startMonitoringFrom);
    }

    private ArmedZone(Zone zone, LocalDateTime startMonitoringFrom) {
        this.zone = zone;
        this.startMonitoringFrom = startMonitoringFrom;
    }
}
