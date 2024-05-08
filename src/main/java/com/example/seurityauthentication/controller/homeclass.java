package com.example.seurityauthentication.controller;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class homeclass {

    @GetMapping("/home")
    public String home() {
        return "<h1>Hello World!</h1>";
    }

}
