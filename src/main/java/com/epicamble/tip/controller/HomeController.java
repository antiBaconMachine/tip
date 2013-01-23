package com.epicamble.tip.controller;

import java.io.IOException;
import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author Ollie Edwards <ollie@codingcraft.co.uk>
 */
@Controller
@RequestMapping(value= {"", "/"})
public class HomeController extends BaseController {
    
    @NotNull
    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView showHome(final HttpServletRequest request, @NotNull Principal principal) throws IOException {
        return this.getMav("home");
    }

}
