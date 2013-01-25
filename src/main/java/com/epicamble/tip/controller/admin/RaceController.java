package com.epicamble.tip.controller.admin;

import com.epicamble.tip.controller.BaseController;
import com.epicamble.tip.model.Race;
import com.epicamble.tip.model.Technology;
import com.epicamble.tip.repository.TechnologyRepository;
import com.epicamble.tip.service.RaceService;
import com.epicamble.tip.util.UNIT_TYPE;
import java.beans.PropertyEditorSupport;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomCollectionEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefaults;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
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

//    @InitBinder
//    public void initBinderAll(final WebDataBinder binder) {
//
//        /**
//         * Technology editor
//         */
//        binder.registerCustomEditor(Collection.class, 
//                new CustomCollectionEditor(Collection.class) {
//                    
//                    @Override
//                    public void setAsText(String e) {
//                        logger.debug("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
//                        super.setAsText(e);
//                    }
//                    
//                    @Override
//                    protected Object convertElement(Object element) {
//                        if (element instanceof Technology) {
//                            logger.debug("We already have a technology");
//                            return element;
//                        }
//                        if (element instanceof String) {
//                            Long id = Long.parseLong((String) element);
//                            Technology tech = technologyRepository.findOne(id);
//                            logger.debug("Retrieved terchnology {}",tech);
//                            return tech;
//                        }
//                        logger.warn("Failed to convert technology {}", element);
//                        return null;
//                    }
//        });
//    }
    
    @InitBinder
    public void initBinderAll(final WebDataBinder binder) {

        /**
         * Technology editor
         */
        binder.registerCustomEditor(Technology.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String tech) {
                logger.debug("Converting string to technology {}", tech);
                Long l = Long.parseLong(tech);
                Technology technology = technologyRepository.findOne(l);
                if (technology != null) {
                    logger.debug("found technology {} for string key {}", new Object[]{technology, tech});
                    setValue(technology);
                }
                //setAsString(new String[]{tech});
            }
        });
                
    }

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
    public ModelAndView create(@ModelAttribute("race") Race race, BindingResult result, RedirectAttributes redirectAttributes) {
        ModelAndView mav = new ModelAndView("redirect:/admin/races");
        logger.debug("Creating new race {} with binding result {}", new Object[]{race, result.getModel()});
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
