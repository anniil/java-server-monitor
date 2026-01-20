package com.anil.controller;

// import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@CrossOrigin(origins = "http://localhost:5174", allowCredentials = "true")
public class LoginController {
    
    @GetMapping("/")
    public String showLoginPage() {
        return "login"; // This refers to login.html in the templates directory
    }

    @GetMapping("/logout")
    public String handleLogout() {
        return "redirect:/";
    }

    @PostMapping("/login")
    public String handleLogin() {
        return "dashboard";
    }

}
