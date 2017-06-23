package com.findwise.kerberos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ProtectedResourceController:
 *
 * This is a standard Spring controller enabling us to
 * forward a JSP page, when needed.
 *
 * @author Peter Gylling - email: peter.jorgensen@findwise.com
 */
@Controller
public class ProtectedResourceController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    /**
     * !! NOTE - Kerberos configuration trick #4 !!
     * This method is important for Spring Security to actually
     * work as intended. Spring Security uses the login page
     * to perform an automated login for the user of the browser.
     *
     * @param model - standard parameter
     * @return
     */
    @RequestMapping("/login")
    public String helloWorld(Model model) {
        return "login";
    }

    @RequestMapping("/protected")
    public String protectedPage(Model model) {
        return "protected";
    }
}
