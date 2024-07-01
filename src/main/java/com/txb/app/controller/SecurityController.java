package com.txb.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SecurityController {


    @GetMapping("/hello")
    public String hello(){
        return "1223";
    }
}
