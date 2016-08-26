package com.matsnowak.smartalarm.core;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.matsnowak.smartalarm.core.exceptions.ListenerArleadyRegisteredException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Created by Mateusz Nowak on 17.08.2016.
 */

@Component
public class EventService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Map<Class<?>, List<EventListener<?>>> listeners = Maps.newConcurrentMap();

    public <T extends Event> void publish(T event) {
        logger.info("Event published " + event);
        List<EventListener<?>> eventListeners = listeners.get(event.getClass());
        if (eventListeners != null) {
            for (int i = 0; i <eventListeners.size() ; i++) {
                EventListener<T> eventListener = (EventListener<T>) eventListeners.get(i);
                eventListener.listen(event);
            }
        }
    }

    public <T extends Event>  void register(EventListener<T> eventListener, Class<T> eventClass) {
        logger.info("EventListener registered " + eventListener);
        List<EventListener<? extends Event>> eventListeners = listeners.get(eventClass);
        if (eventListeners == null){
            eventListeners = Lists.newLinkedList();
            listeners.put(eventClass, eventListeners);
        }
        if (eventListeners.contains(eventListener)) {
            throw new ListenerArleadyRegisteredException();
        }
        eventListeners.add(eventListener);
    }

}
