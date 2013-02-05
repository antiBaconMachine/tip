package com.epicamble.tip.service;

import com.epicamble.tip.model.Match;
import com.epicamble.tip.model.Player;
import com.epicamble.tip.model.Race;
import java.util.List;

/**
 *
 * @author Ollie Edwards <ollie@codingcraft.co.uk>
 */
public interface MatchService {
    
    public Match findByHandle(String handle);
    
    public Match addPlayer(Match match, Player player);
    
    public List<Race> getRaceSelection(String matchId); 
    
}
