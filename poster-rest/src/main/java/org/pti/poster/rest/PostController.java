package org.pti.poster.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class PostController {

    @RequestMapping("/health")
    public  String test(){
        return "Up";
    }
}