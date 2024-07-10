package com.asm.immoManager.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required.")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email should be valid.")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @NotBlank(message = "Phone is required.")
    @Column(name = "phone", nullable = false)
    private String phone;

    @Column(name = "is_admin", nullable = false)
    private boolean admin;

    @NotBlank(message = "Password is required.")
    @Column(name = "password", nullable = false)
    private String password;

    // One user to many properties
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Property> property;

    // One user to many tenants
    @JsonIgnore
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Tenant> tenants;
}