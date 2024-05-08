package com.example.seurityauthentication.repository;

import com.example.seurityauthentication.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface authenticateRepo extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}
