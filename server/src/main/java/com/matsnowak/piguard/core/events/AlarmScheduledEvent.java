package com.matsnowak.piguard.core.events;

import com.matsnowak.piguard.core.Event;
import com.matsnowak.piguard.core.EventType;

import java.util.List;
import java.util.Set;

import static com.matsnowak.piguard.core.EventType.ALARM_SCHEDULED;

/**
 * Created by Mateusz Nowak on 16.09.2016.
 */
public class AlarmScheduledEvent extends Event{

    private final int triggeredBySensorId;
    private final Set<Integer> armedZonesIds;
    private final int delayInSeconds;

    public AlarmScheduledEvent(int triggeredBySensorId, Set<Integer> armedZonesIds, int delayInSeconds) {
        this.triggeredBySensorId = triggeredBySensorId;
        this.armedZonesIds = armedZonesIds;
        this.delayInSeconds = delayInSeconds;
    }

    @Override
    public EventType getEventType() {
        return ALARM_SCHEDULED;
    }

    public int getTriggeredBySensorId() {
        return triggeredBySensorId;
    }

    public int getDelayInSeconds() {
        return delayInSeconds;
    }

    public Set<Integer> getArmedZonesIds() {
        return armedZonesIds;
    }

    @Override
    public String toString() {
        return "AlarmScheduledEvent{" +
                "triggeredBySensorId=" + triggeredBySensorId +
                ", armedZonesIds=" + armedZonesIds +
                ", delayInSeconds=" + delayInSeconds +
                "} " + super.toString();
    }
}
