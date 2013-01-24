package com.epicamble.tip.controller.admin;

import com.epicamble.tip.controller.BaseController;
import com.epicamble.tip.model.Race;
import com.epicamble.tip.repository.TechnologyRepository;
import com.epicamble.tip.service.RaceService;
import com.epicamble.tip.util.UNIT_TYPE;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Ollie Edwards <oliver.s.edwards@gmail.com>
 */
@Controller
@RequestMapping("/admin/races")
public class RaceController extends BaseController {
    
    Logger logger = LoggerFactory.getLogger(RaceController.class); 

    @Autowired
    private RaceService raceService;
    
    @Autowired
    private TechnologyRepository technologyRepository;

    @RequestMapping
    public ModelAndView list(@PageableDefaults(pageNumber = 0, value = 30) Pageable pageable) {
//        if (pageable.getSort() == null) {
//            pageable = new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), new Sort("name"));
//        }
        ModelAndView mav = getMav("race/list");
        //Page page = raceService.findAll(pageable);
        List<Race> races = raceService.findAll();
        mav.addObject("races", races);
        return mav;
    }

    @RequestMapping("/add")
    public ModelAndView add() {
        ModelAndView mav = getMav("race/edit");
        Race race = new Race();
        mav.addObject("race", race);
        mav.addObject("technologies", technologyRepository.findAll());
        mav.addObject("UNITS", UNIT_TYPE.values());
        return mav;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ModelAndView create(@ModelAttribute Race race, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("redirect:/admin/races");
        logger.debug("Creating new race {} with binding result {}",new Object[]{race, result});
        if (result.hasErrors()) {
            mav.setViewName("redirect:/admin/races/add");
            return mav;
        }

        race = raceService.create(race);
        mav.addObject("id", race.getId());

        redirectAttributes.addFlashAttribute("SUCCESS_MESSAGE", "Successfully saved " + race.getName());

        return mav;
    }
}
