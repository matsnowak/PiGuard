package com.matsnowak.smartalarm.core;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.matsnowak.smartalarm.core.exceptions.ListenerArleadyRegisteredException;

import java.util.List;
import java.util.Map;

/**
 * Created by Mateusz Nowak on 17.08.2016.
 */
public class EventBus {
    private Map<Class<?>, List<EventListener<?>>> listeners = Maps.newConcurrentMap();

    <T extends Event> void publish(T event) {
        List<EventListener<?>> eventListeners = listeners.get(event.getClass());
        if (eventListeners != null) {
            for (int i = 0; i <eventListeners.size() ; i++) {
                EventListener<T> eventListener = (EventListener<T>) eventListeners.get(i);
                eventListener.listen(event);
            }
        }
    }

    void register(EventListener eventListener) {
        List<EventListener<? extends Event>> eventListeners = listeners.get(eventListener.getEventType());
        if (eventListeners == null){
            eventListeners = Lists.newLinkedList();
            listeners.put(eventListener.getEventType(), eventListeners);
        }
        if (eventListeners.contains(eventListener)) {
            throw new ListenerArleadyRegisteredException();
        }
        eventListeners.add(eventListener);
    }

}
