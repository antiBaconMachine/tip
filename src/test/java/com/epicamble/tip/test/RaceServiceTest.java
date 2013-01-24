package com.epicamble.tip.test;

import com.epicamble.tip.model.Race;
import com.epicamble.tip.service.RaceService;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Set;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
public class RaceServiceTest extends AbstractTest {
    
    private Logger logger = LoggerFactory.getLogger(RaceServiceTest.class);
    
    @Autowired
    private RaceService raceService;
    
    @Test
    public void testFindByName() throws FileNotFoundException, IOException {
        Set<Race> racesFromJson = raceImporter.getRacesFromJSON();
        Race testRace = racesFromJson.iterator().next();
        Race race = raceService.findByName(testRace.getName());
        assertNotNull(race);
        assertEquals(testRace, race);
    }
    
}
