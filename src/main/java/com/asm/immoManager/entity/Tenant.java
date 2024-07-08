package com.asm.immoManager.entity;

import java.time.LocalDate;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "tenant")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tenant {

    @Id
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

    // Many tenants to one user
    @JsonBackReference
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // One tenant to many properties
    @JsonManagedReference
    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Property> properties;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }
}
