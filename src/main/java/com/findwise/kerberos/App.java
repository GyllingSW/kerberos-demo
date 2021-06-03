package com.findwise.kerberos;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * SpringBootApplication:
 *
 * !! NOTE - Kerberos configuration trick #1 !!
 * - It's important to disable the auto configuration of Spring Security
 * - It's initialized if the SecurityAutoConfiguration.class is not excluded
 *
 * A side effect here is, that you must provide you own login page as the
 * default from Spring Security basic is not available.
 *
 * @author Peter Gylling - email: peter.jorgensen@findwise.com
 * @see com.findwise.kerberos.controller.ProtectedResourceController
 */
@Configuration
@EnableAutoConfiguration(exclude = SecurityAutoConfiguration.class)
@ComponentScan
public class App {
    private final static Logger log = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception {
        SpringApplication.run(App.class, args);
        log.info("Application startup completed");
    }
}