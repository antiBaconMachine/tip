package com.epicamble.tip.controller;

import com.epicamble.tip.model.Match;
import com.epicamble.tip.model.User;
import com.epicamble.tip.repository.MatchRepository;
import java.io.IOException;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
@Controller
@RequestMapping("/match")
public class MatchController extends BaseController {
    
    private final static Logger logger = LoggerFactory.getLogger(MatchController.class);
    
    @Autowired
    private MatchRepository matchRepository;
    @Autowired
    private ObjectMapper objectMapper;
    
    //TODO change to post only
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView newMatch(final HttpServletRequest request, @NotNull Principal principal) throws IOException {
        logger.debug("New match requested by {}", principal);
        ModelAndView mav = this.getMav("match/edit");
        Match match = new Match();
        if (principal != null) {
            match.setOwner((User) principal);
        }
        matchRepository.save(match);
        mav.addObject("match", match);
        mav.addObject("matchJSON", objectMapper.writeValueAsString(match));
        return mav;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ModelAndView showMatch(final HttpServletRequest request,
            @NotNull Principal principal,
            @PathVariable String id) throws IOException {

        ModelAndView mav = this.getMav("match/edit");
        Match match = matchRepository.findOne(Long.parseLong(id));
        mav.addObject("match", match);
        mav.addObject("matchJSON", objectMapper.writeValueAsString(match));
        return mav; 
    }
}
