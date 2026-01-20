package com.anil.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.anil.model.Role;
import com.anil.model.Users;
import com.anil.repo.RoleRepo;
import com.anil.repo.UserRepo;

@Component
public class DataInitializer implements CommandLineRunner {
    
    @Autowired
    private UserRepo userRepository;

    @Autowired
    private RoleRepo roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {

        if (roleRepository.count() == 0) {
            Role admin = new Role();

            admin.setName("ROLE_ADMIN");
            Role viewer = new Role();
            viewer.setName("ROLE_VIEWER");
            
            roleRepository.save(admin);
            roleRepository.save(viewer);
        }

        if (userRepository.findByUsername("admin") == null) {
            Users admin = new Users();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("a@123"));

            admin.setRole(roleRepository.findByName("ROLE_ADMIN"));

            userRepository.save(admin);
            System.out.println("Admin created: admin/a@123");
        }
        
        if (userRepository.findByUsername("viewer") == null) {
            Users viewer = new Users();
            viewer.setUsername("viewer");
            viewer.setPassword(passwordEncoder.encode("v@123"));

            viewer.setRole(roleRepository.findByName("ROLE_VIEWER"));
            
            userRepository.save(viewer);
            System.out.println("Viewer created: viewer/v@123");
        }
    }
}
