package com.epicamble.tip.test;

import com.epicamble.tip.data.RaceImporter;
import java.io.IOException;
import junit.framework.TestCase;
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
public abstract class AbstractTest extends TestCase{
    
    @Autowired
    protected RaceImporter raceImporter;
    
//    I think this does auto rollback?
//    @Before
//    public void setup() throws IOException {
//        raceImporter.importRaces();
//    };

}
