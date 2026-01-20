package com.anil.model;

import org.springframework.stereotype.Component;

import jakarta.persistence.*;
import lombok.*;

@Component
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
public class Role {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String name; // e.g., ROLE_ADMIN, ROLE_VIEWER
}
