/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epicamble.tip.service.impl;

import com.epicamble.tip.model.Match;
import com.epicamble.tip.model.Player;
import com.epicamble.tip.model.Race;
import com.epicamble.tip.repository.MatchRepository;
import com.epicamble.tip.repository.RaceRepository;
import com.epicamble.tip.test.AbstractTest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.*;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.util.ReflectionTestUtils;

/**
 *
 * @author Ollie Edwards <ollie@codingcraft.co.uk>
 */
public class MatchServiceImplTest extends AbstractTest {
    
    Logger logger = LoggerFactory.getLogger(MatchServiceImplTest.class);
    
    private MatchServiceImpl matchService;
    private MatchRepository matchRepositoryMock;
    private RaceRepository raceRepositoryMock;
    
    public MatchServiceImplTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    @Override
    public void setUp() {
        matchService = new MatchServiceImpl();
        matchRepositoryMock = mock(MatchRepository.class);
        raceRepositoryMock = mock(RaceRepository.class);
        ReflectionTestUtils.setField(matchService, "matchRepository", matchRepositoryMock);
        ReflectionTestUtils.setField(matchService, "raceRepository", raceRepositoryMock);
    }
    
    @After
    @Override
    public void tearDown() {
    }

    /**
     * Test of getRaceSelection method, of class MatchServiceImpl.
     */
    @Test
    public void testGetRaceSelection() throws FileNotFoundException, IOException {
        List<Race> allraces = ((List<Race>) getRacesFromJson()).subList(0, 6);
        List<Race> pickedRaces = new ArrayList();
        List<Player> players = new ArrayList<Player>();
        Match match = new Match();
        match.setName("Test match");
        for (int i=0; i<3; i++) {
            Player p = new Player();
            p.setName("player"+i);
            Race race = allraces.get(i);
            p.setRace(race);
            pickedRaces.add(race);
            p.setMatch(match);
            players.add(p);
        }
        match.setPlayers(players);
         
        when(raceRepositoryMock.findAll()).thenReturn(allraces);
        when(matchRepositoryMock.findOne(any(Long.class))).thenReturn(match);
        
        List<Race> selection = matchService.getRaceSelection("111");
        logger.debug("got race selection {}", selection);
        
        //DOn't verrify repository methods called as we intend to make a smarter query
        assertTrue("race selection does no equal 3, is"+selection.size(), selection.size() == 3);
        
        for (Race r : selection) {
            assertTrue("selection should not contain a picked race", !pickedRaces.contains(r));
        }
        
    }
}
