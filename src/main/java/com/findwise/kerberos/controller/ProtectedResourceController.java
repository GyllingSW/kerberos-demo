package com.findwise.kerberos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Peter Gylling - email: peter.jorgensen@findwise.com
 */
@Controller
public class ProtectedResourceController {

    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/login")
    public String helloWorld(Model model) {
        return "login";
    }

    @RequestMapping("/protected")
    public String protectedPage(Model model) {
        return "protected";
    }
}
