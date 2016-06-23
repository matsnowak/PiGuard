package com.matsnowak.smartalarm.main.exceptions;

/**
 * Created by Mateusz on 21.06.2016.
 */
public class SensorException extends Exception  {
    public SensorException(String message) {
        super(message);
    }

    public SensorException(String message, Throwable cause) {
        super(message, cause);
    }

    public SensorException(Throwable cause) {
        super(cause);
    }


}
