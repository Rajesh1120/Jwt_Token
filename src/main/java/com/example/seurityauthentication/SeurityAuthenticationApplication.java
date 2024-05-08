package com.example.seurityauthentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
//@ComponentScan("com.example.seurityauthentication.service")
public class SeurityAuthenticationApplication {
    public static void main(String[] args) {
        SpringApplication.run(SeurityAuthenticationApplication.class, args);
    }

}
