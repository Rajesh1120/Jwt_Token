package com.example.seurityauthentication.service;

import com.example.seurityauthentication.model.User;
import com.example.seurityauthentication.repository.authenticateRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class authenticationServiceImpl implements authenticationService{


    @Autowired
    private authenticateRepo repo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user) {
        System.out.println("addUser");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println(user.getPassword());
        return repo.save(user);
    }
}
