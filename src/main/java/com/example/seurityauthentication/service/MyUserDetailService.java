package com.example.seurityauthentication.service;

import com.example.seurityauthentication.model.User;
import com.example.seurityauthentication.repository.authenticateRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private authenticateRepo repo;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User userdata = repo.findByUsername(username);
        System.out.println(userdata);
        return new UserDetailsImpl(userdata);
    }


}
