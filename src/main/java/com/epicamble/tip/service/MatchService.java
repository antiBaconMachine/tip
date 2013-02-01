package com.epicamble.tip.service;

import com.epicamble.tip.model.Race;
import java.util.List;

/**
 *
 * @author Ollie Edwards <ollie@codingcraft.co.uk>
 */
public interface MatchService {
    
    public List<Race> getRaceSelection(String matchId); 
    
}
