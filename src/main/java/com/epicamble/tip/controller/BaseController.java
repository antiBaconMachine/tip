package com.epicamble.tip.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author Ollie Edwards <ollie@codingcraft.co.uk>
 */
abstract class BaseController extends AbstractController {

    @Value("${js.debug}")
    protected String jsDebugLevel;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest hsr, HttpServletResponse hsr1) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
    protected ModelAndView getMav(String viewName) {
        ModelAndView mav = new ModelAndView(viewName);
        mav.addObject("jsDebugLevel", jsDebugLevel);
        return mav;
    }
}
