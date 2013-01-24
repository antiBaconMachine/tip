package com.epicamble.tip.controller.rest;

import com.epicamble.tip.model.Race;
import com.epicamble.tip.service.RaceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
@Controller
@RequestMapping("/rest")
public class RacesController {
    
    @Autowired
    private RaceService raceService;
    
    @RequestMapping(value="/races", method = RequestMethod.GET)
    public @ResponseBody List<Race> getRaces() {
        return raceService.findAll();
    }
    
}