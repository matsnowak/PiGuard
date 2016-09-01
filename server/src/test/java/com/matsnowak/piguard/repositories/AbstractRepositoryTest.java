package com.matsnowak.piguard.repositories;

import com.matsnowak.piguard.main.ServerApplication;
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
