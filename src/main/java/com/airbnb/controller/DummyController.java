package com.airbnb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/dummy")
public class DummyController {

    //http://localhost:8080/api/v1/dummy/getMessage
    @GetMapping("/getMessage")
    public String printMsg(){
        return "Hello Earth..";
    }

}
