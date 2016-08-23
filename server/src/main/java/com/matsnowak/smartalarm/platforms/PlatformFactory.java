package com.matsnowak.smartalarm.platforms;

import com.matsnowak.smartalarm.core.Platform;
import com.matsnowak.smartalarm.platforms.rpi3mb.RaspberyPi3Platform;
import com.matsnowak.smartalarm.platforms.stub.StubPlatform;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Mateusz Nowak on 23.08.2016.
 */
public class PlatformFactory {
    private static Logger logger = LoggerFactory.getLogger(PlatformFactory.class);
    private static String OS = null;

    public static Platform newInstance() {
        if(getOsName().contains("Windows")) {
            logger.info("Create StubPlatform");
            return new StubPlatform();
        } else { // probably raspberyPi
            logger.info("Create RaspberryPi3Platform");
            return new RaspberyPi3Platform();
        }
    }

    private static String getOsName() {
        if (OS == null) {
            OS = System.getProperty("os.name");
        }
        return OS;
    }
}
