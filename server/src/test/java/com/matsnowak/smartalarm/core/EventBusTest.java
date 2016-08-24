package com.matsnowak.smartalarm.core;

import com.matsnowak.smartalarm.core.exceptions.ListenerArleadyRegisteredException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Mateusz Nowak on 17.08.2016.
 */

@RunWith(MockitoJUnitRunner.class)
public class EventBusTest {
    private final String UUID_SEED = "random uuid";

    @Mock
    EventListener<TestEvent> mockEventListener;


    @Test
    public void shouldPublishEventToListeners() throws Exception {
        // given
        final EventBus eventBus = new EventBus();
        when(mockEventListener.getEventType()).thenReturn(TestEvent.class);
        final TestEvent testEvent = getTestEvent();
        eventBus.register(mockEventListener);

        // when
        eventBus.publish(testEvent);

        // then
        verify(mockEventListener, times(1)).listen(testEvent);
    }

    @Test
    public void shouldThrowExceptionWhenTheSameListenerRegistrationTwice() throws Exception {
        // given
        final EventBus eventBus = new EventBus();
        when(mockEventListener.getEventType()).thenReturn(TestEvent.class);

        // when then
        eventBus.register(mockEventListener);
        assertThatThrownBy( () -> eventBus.register(mockEventListener))
                .isInstanceOf(ListenerArleadyRegisteredException.class);
    }

    private TestEvent getTestEvent() {
        return new TestEvent();
    }

    public class TestEvent extends Event {


        @Override
        public EventType getEventType() {
            return EventType.SENSOR_ACTIVATED;
        }
    }


}