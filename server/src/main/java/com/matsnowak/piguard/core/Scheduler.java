package com.matsnowak.piguard.core;

import com.matsnowak.piguard.controllers.AuthorizationController;
import com.matsnowak.piguard.core.events.AlarmLaunchedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Mateusz Nowak on 16.09.2016.
 */
@Component
public class Scheduler {
    private static Logger logger = LoggerFactory.getLogger(Scheduler.class);

    @Autowired
    private EventService eventService;

    @Autowired
    private AuthorizationController authorizationController;

    private Map<Integer, AlarmSchedule> timerMap = new ConcurrentHashMap<>();

    private Timer logoutTimer;


    // TODO possible add cancel logout


    public void scheduleAlarm(Set<Integer> armedZonesIds, Integer delayInSeconds, int triggeredBySensorId) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime targetTime = now.plus(delayInSeconds, ChronoUnit.SECONDS);
        armedZonesIds.forEach(id -> {
            AlarmSchedule alarmSchedule = timerMap.get(id);
            if (alarmSchedule == null) {
                putAlarmSchedule(id, delayInSeconds, targetTime);
                logger.debug("Added alarm schedule for zone " + id + " with delay " + +delayInSeconds);
            } else {
                if (targetTime.isBefore(alarmSchedule.getAlarmTime())) {
                    alarmSchedule.getTimer().cancel();
                    putAlarmSchedule(id, delayInSeconds, targetTime);
                    logger.debug("Replaced alarm schedule for zone " + id + " with delay " + +delayInSeconds);
                } else {
                    logger.debug("Skipped alarm schedule " + id + " with delay " + +delayInSeconds + " because closer schedule exists");
                }
            }
        });
    }

    public void cancelAlarmSchedule(Integer armedZoneId) {
        AlarmSchedule alarmSchedule = timerMap.get(armedZoneId);
        if (alarmSchedule != null) {
            alarmSchedule.getTimer().cancel();
            timerMap.remove(armedZoneId);
        }
    }

    private void putAlarmSchedule(int armedZoneId, int delayInSeconds, LocalDateTime targetTime) {
        Timer timer = new Timer("Alarm schedule for armed zone " + armedZoneId);
        timer.schedule(new AlarmStartDelayedTimer(armedZoneId, eventService), delayInSeconds * 1000);
        timerMap.put(armedZoneId, new AlarmSchedule(timer, targetTime));
    }

    public void scheduleLogout(Integer delayInSeconds) {

        if (logoutTimer != null) {
            logoutTimer.cancel();
            logoutTimer = null;
        }

        logoutTimer = new Timer("Logout schedule");
        logoutTimer.schedule(new LogoutTimer(authorizationController), delayInSeconds);
    }


    private class AlarmStartDelayedTimer extends TimerTask {
        private final int armedZoneId;
        private final EventService eventService;

        public AlarmStartDelayedTimer(int armedZoneId, EventService eventService) {
            this.armedZoneId = armedZoneId;
            this.eventService = eventService;
        }

        @Override
        public void run() {
            eventService.publish(new AlarmLaunchedEvent(armedZoneId));
            timerMap.remove(armedZoneId);
        }
    }

    private class AlarmSchedule {
        private final Timer timer;
        private final LocalDateTime alarmTime;

        public AlarmSchedule(Timer timer, LocalDateTime alarmTime) {
            this.timer = timer;
            this.alarmTime = alarmTime;
        }

        public Timer getTimer() {
            return timer;
        }

        public LocalDateTime getAlarmTime() {
            return alarmTime;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            AlarmSchedule that = (AlarmSchedule) o;
            return Objects.equals(getTimer(), that.getTimer()) &&
                    Objects.equals(getAlarmTime(), that.getAlarmTime());
        }

        @Override
        public int hashCode() {
            return Objects.hash(getTimer(), getAlarmTime());
        }
    }

    private class LogoutTimer extends TimerTask{
        private final AuthorizationController authorizationController;
        public LogoutTimer(AuthorizationController authorizationController) {
            this.authorizationController = authorizationController;
        }

        @Override
        public void run() {
            logger.info("Logging out");
//            authorizationController.logout();
            SecurityContextHolder.getContext().setAuthentication(null);
        }
    }
}
