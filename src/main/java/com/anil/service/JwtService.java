package com.anil.service;


import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;


import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtService {

    @Value("${jwt.secret}")
    private String secretBase64;

    @Value("${jwt.expiration-ms}")
    private long expirationMs;

    private SecretKey signKey;

    // public JwtService() {
    // // String secretkey;
    // // try {
    // // KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
    // // SecretKey sk = keyGen.generateKey();
    // // secretkey = Base64.getEncoder().encodeToString(sk.getEncoded());
    // // } catch (NoSuchAlgorithmException e) {
    // // throw new RuntimeException(e);
    // // }

    // // byte[] keyBytes = Decoders.BASE64.decode(secretkey);
    // // signKey = Keys.hmacShaKeyFor(keyBytes);

    // byte[] keyBytes = Decoders.BASE64.decode(secretBase64);
    // signKey = Keys.hmacShaKeyFor(keyBytes);
    // }

    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(secretBase64);
        this.signKey = Keys.hmacShaKeyFor(keyBytes);
    }
    
    public String generateToken(UserDetails userDetails) {

        Map<String, Object> claims = new HashMap<>();
        // add roles as claim (single role expected)
        if (!userDetails.getAuthorities().isEmpty()) {
            claims.put("roles", userDetails.getAuthorities().stream()
                    .map(Object::toString).toList());
        }
        return createToken(claims, userDetails.getUsername());

    }

    private String createToken(Map<String, Object> claims, String subject) {

        Date now = new Date();
        Date expiry = new Date(now.getTime() + expirationMs);

        return Jwts.builder()
                .claims()
                .add(claims)
                .subject(subject)
                .issuedAt(now)
                .expiration(expiry)
                .and()
                .signWith(signKey)
                .compact();
    }

    public Optional<String> extractUsername(String token) {
        try {
            return Optional.of(extractAllClaims(token).getSubject());
        } catch (JwtException | IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public Optional<String> extractRole(String token) {
        try {
            return Optional.ofNullable(extractAllClaims(token).get("role", String.class));
        } catch (JwtException | IllegalArgumentException e) {
            return Optional.empty();
        }
    }

    public boolean isTokenValid(String token) {
        try {
            Claims c = extractAllClaims(token);
            return !c.getExpiration().before(new Date());
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(signKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public int getJwtExpirationSeconds() {
        return (int) (expirationMs / 1000L);
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractUsername(token).orElse("null");
        return (userName.equals(userDetails.getUsername()) && isTokenValid(token));
    }

}
