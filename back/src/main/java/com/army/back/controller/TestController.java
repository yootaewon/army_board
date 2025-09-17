package com.army.back.controller;


import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mysql.cj.protocol.x.Ok;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
public class TestController {

    @Autowired
    private DataSource dataSource;



    @GetMapping("/hello")
    public String hello() {
        return "Hello world";
    }

    @GetMapping("/db/test")
    public String db() {
        try(Connection conn = dataSource.getConnection()){
            return "DB connection successful";
        }catch(Exception e){
            return "DB connetion failed";   
        }
    }

    @RequestMapping(value="proxy", method=RequestMethod.GET)
    public String proxy(@RequestParam String param) {
        return param;
        
    }
}

