package com.epicamble.tip.controller.rest;

import com.epicamble.tip.model.Match;
import com.epicamble.tip.model.Player;
import com.epicamble.tip.model.Race;
import com.epicamble.tip.service.MatchService;
import com.epicamble.tip.service.RaceService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    
    private final static Logger logger = LoggerFactory.getLogger(RestController.class);
    
    @Autowired
    private RaceService raceService;
    @Autowired
    private MatchService matchService;
    
    @RequestMapping(value="/races", method = RequestMethod.GET)
    public @ResponseBody List<Race> getRaces() {
        return raceService.findAll();
    }
    
    @RequestMapping(value="/match/{handle}", method = RequestMethod.GET)
    public @ResponseBody Match getMatch(@PathVariable String handle) {
        return matchService.findByHandle(handle);
    }
    
    @RequestMapping(value="/match/{handle}/raceSelection", method = RequestMethod.GET)
    public @ResponseBody List<Race> getRaceSelectionForMatch(@PathVariable String handle) {
        return matchService.getRaceSelection(handle);
    }
    
    @RequestMapping(value="/match/{handle}/addPlayer", method = RequestMethod.POST)
    public @ResponseBody Match addPlayer(@PathVariable String handle, @RequestBody Player player) {
        Match match = matchService.findByHandle(handle);
        logger.debug("Adding player {} to match {}", new Object[]{player,match});
        return matchService.addPlayer(match, player);
    }
}
