package com.epicamble.tip.controller.rest;

import com.epicamble.tip.model.Match;
import com.epicamble.tip.model.Player;
import com.epicamble.tip.model.Race;
import com.epicamble.tip.service.MatchService;
import com.epicamble.tip.service.RaceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
@Controller
@RequestMapping("/rest")
public class RestController {
    
    @Autowired
    private RaceService raceService;
    @Autowired
    private MatchService matchService;
    
    @RequestMapping(value="/races", method = RequestMethod.GET)
    public @ResponseBody List<Race> getRaces() {
        return raceService.findAll();
    }
    
    @RequestMapping(value="/match/{matchId}/raceSelection", method = RequestMethod.GET)
    public @ResponseBody List<Race> getRaceSelectionForMatch(@PathVariable String matchId) {
        return matchService.getRaceSelection(matchId);
    }
    
    @RequestMapping(value="/match/{matchId}/addPlayer", method = RequestMethod.PUT)
    public @ResponseBody void addPlayer(@PathVariable String matchId, @RequestBody Player player) {
        Match match = matchService.findByHandle(matchId);
        matchService.addPlayer(match, player);
    }
}