package com.example.seurityauthentication.controller;

import com.example.seurityauthentication.model.User;
//import com.example.seurityauthentication.service.JwtService;
import com.example.seurityauthentication.service.JwtService;
import com.example.seurityauthentication.service.authenticationService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/")
@AllArgsConstructor

public class authenticationController {


    @Autowired
    private authenticationService service;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("signup")
    public ResponseEntity<User> signUp(@RequestBody User user){
        User add_user = service.addUser(user);
        return new ResponseEntity<>(add_user,HttpStatus.CREATED);
    }
    @PostMapping("signin")
    public String signIn(@RequestBody User user){

        Authentication authentication= authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(user.getUsername());

        }
        else{
            return "Login Failed";
        }
    }

}
