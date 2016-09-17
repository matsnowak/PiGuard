package com.matsnowak.piguard.core.events;

import com.matsnowak.piguard.core.Event;
import com.matsnowak.piguard.core.EventType;

import java.util.Objects;

/**
 * Created by Mateusz Nowak on 17.09.2016.
 */
public class AlarmLaunchedEvent extends Event {
    private Integer armedZoneId;

    public AlarmLaunchedEvent(int armedZoneId) {
        this.armedZoneId = armedZoneId;
    }

    public Integer getArmedZoneId() {
        return armedZoneId;
    }

    @Override
    public EventType getEventType() {
        return EventType.ALARM_LAUNCHED;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        AlarmLaunchedEvent that = (AlarmLaunchedEvent) o;
        return Objects.equals(getArmedZoneId(), that.getArmedZoneId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), getArmedZoneId());
    }

    @Override
    public String toString() {
        return "AlarmLaunchedEvent{" +
                "armedZoneId=" + armedZoneId +
                "} " + super.toString();
    }
}
