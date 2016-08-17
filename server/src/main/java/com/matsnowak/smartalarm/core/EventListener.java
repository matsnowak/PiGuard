package com.matsnowak.smartalarm.core;

/**
 * Created by Mateusz Nowak on 17.08.2016.
 */
public interface EventListener<T extends Event> {
    void listen(T event);

    Class<T> getEventType();
}
