package com.matsnowak.smartalarm.repositories;

import com.matsnowak.smartalarm.main.ServerApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Mateusz Nowak on 18.08.2016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ServerApplication.class)
public abstract class AbstractRepositoryTest {
}
