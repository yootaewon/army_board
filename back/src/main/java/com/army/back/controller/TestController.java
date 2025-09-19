package com.army.back.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api")
public class TestController {

    @GetMapping("/hello")
    public  String hello() {
        System.out.println("요청 왔어요");
        return "Hello world";  
    }
}



