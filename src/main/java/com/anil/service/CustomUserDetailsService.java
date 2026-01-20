package com.anil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.anil.model.CustomUserDetails;
import com.anil.model.Users;
import com.anil.repo.UserRepo;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepo userRepo;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Implementation to load user from database
        Users user = (Users) userRepo.findByUsername(username);
        if (user == null) {
            System.out.println("User not found");
            throw new UsernameNotFoundException("User not found");
        }
        
        return new CustomUserDetails(user);

    }

}
