package com.matsnowak.smartalarm.core;


import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

/**
 * Created by Mateusz Nowak on 17.08.2016.
 */
public abstract class Event {
    protected UUID uuid;
    protected LocalDateTime created;

    public abstract EventType getEventType();

    public UUID getUuid() {
        return uuid;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public Event() {
        this.uuid = UUID.randomUUID();
        this.created = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Event{" +
                "uuid=" + uuid +
                ", created=" + created +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return Objects.equals(uuid, event.uuid) &&
                Objects.equals(created, event.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid, created);
    }
}
