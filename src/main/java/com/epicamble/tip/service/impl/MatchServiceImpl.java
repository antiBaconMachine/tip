package com.epicamble.tip.service.impl;

import com.epicamble.tip.model.Player;
import com.epicamble.tip.model.Race;
import com.epicamble.tip.repository.MatchRepository;
import com.epicamble.tip.repository.RaceRepository;
import com.epicamble.tip.service.MatchService;
import java.util.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Ollie Edwards <ollie@codingcraft.co.uk>
 */
@Component
public class MatchServiceImpl implements MatchService {
    
    Logger logger = LoggerFactory.getLogger(MatchServiceImpl.class);

    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private RaceRepository raceRepository;
    
    /**
     * Return a random selection of 3 available races
     * 
     * for now string key is converted to long id as we haven't implmented string hash urls
     */
    @Override
    public List<Race> getRaceSelection(String matchId) {
        //TODO fancy query to achieve all this
        List<Race> races = raceRepository.findAll();
        List<Player> players = matchRepository.findOne(Long.valueOf(matchId)).getPlayers();
        for (Player p : players) {
            Race r = p.getRace();
            if (races.contains(r)) {
                races.remove(r);
            }
        }
        Collections.shuffle(races);
        if (races.size() > 3) {
            return races.subList(0, 3);
        } else {
            return races;
        }
    }

}
