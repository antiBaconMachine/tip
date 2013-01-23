package com.epicamble.tip.test;

import com.epicamble.tip.data.RaceImporter;
import java.io.IOException;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:root-context.xml"})
@ActiveProfiles("testing")
public abstract class AbstractTest {
    
    @Autowired
    protected RaceImporter raceImporter;
    
    @Before
    public void setup() throws IOException {
        raceImporter.importRaces();
    };

}