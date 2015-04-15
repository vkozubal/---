package org.pti.poster.boot.controllers;

import org.pti.poster.view.StringResponse;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {
    
    @RequestMapping(value = "/hello", method = RequestMethod.GET)
    public StringResponse index() {
        return new StringResponse("Greetings from Spring Boot!");
    }
}
