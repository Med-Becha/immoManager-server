package com.asm.immoManager.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "Tenant")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tenant {

    @Id
    @Column(name = "tenant_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required.")
    @Column(name = "name", nullable = false)
    private String username;

    @NotBlank(message = "Email is required.")
    @Email(message = "Email should be valid.")
    @Column(name = "email", nullable = false)
    private String email;

    @NotBlank(message = "Phone is required.")
    @Column(name = "phone", nullable = false)
    private String phone;

    @NotBlank(message = "CIN is required.")
    @Column(name = "cin", nullable = false)
    private String cin;

    @NotBlank(message = "Address is required.")
    @Column(name = "adress", nullable = false)
    private String adress;

    @NotBlank(message = "Job is required.")
    @Column(name = "job", nullable = false)
    private String job;

    @NotBlank(message = "Budget is required.")
    @Column(name = "budget", nullable = false)
    private String budget;

    @Column(name = "remark")
    private String remark;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }
}