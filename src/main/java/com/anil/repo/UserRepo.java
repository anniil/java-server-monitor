package com.anil.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.anil.model.Users;

public interface UserRepo extends JpaRepository<Users, Long> {

    Users findByUsername(String username);
    
}
