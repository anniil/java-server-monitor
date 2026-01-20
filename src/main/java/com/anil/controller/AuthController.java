package com.anil.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.anil.model.CustomUserDetails;
import com.anil.model.LoginRequest;
import com.anil.model.LoginResponse;
import com.anil.service.CustomUserDetailsService;
import com.anil.service.JwtService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "http://localhost:5174", allowCredentials = "true")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtService jwtService;

    @GetMapping("/me")
    public ResponseEntity<?> me(Authentication authentication, HttpServletRequest request) {

        if (authentication == null) {
            return ResponseEntity.status(401).build();
        }

        CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();
        System.out.println(user.getUsername());
        return ResponseEntity.ok(Map.of(
                "username", user.getUsername(),
                "role", user.getAuthorities()));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request, HttpServletResponse response) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()));

        System.out.println(request.getPassword());
        System.out.println(request.getUsername());

        UserDetails user = userDetailsService.loadUserByUsername(request.getUsername());
        String token = jwtService.generateToken(user);

        System.out.println(token);

        boolean isProd = false; 

        ResponseCookie cookie = ResponseCookie.from("jwt", token)
                .httpOnly(true)
                .secure(isProd)
                .sameSite(isProd ? "None" : "Lax")
                .path("/")
                .maxAge(24 * 60 * 60)
                .build();

        response.addHeader(HttpHeaders.SET_COOKIE, cookie.toString());

        return ResponseEntity.ok(Map.of("status", "login-success"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {

        Cookie cookie = new Cookie("jwt", null);
        cookie.setHttpOnly(true);
        cookie.setSecure(false); // true in prod
        cookie.setPath("/");
        cookie.setMaxAge(0); // delete

        response.addCookie(cookie);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/test-public")
    public String publicTest() {
        return "public ok";
    }

    @GetMapping("/test-secure")
    public String secureTest() {
        return "secure ok (you have valid JWT)";
    }
}
