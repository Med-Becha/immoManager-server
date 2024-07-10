package com.asm.immoManager.entity;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
    private String name;

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
    private String address;

    @NotBlank(message = "Job is required.")
    @Column(name = "job", nullable = false)
    private String job;

    @NotBlank(message = "Budget is required.")
    @Column(name = "budget", nullable = false)
    private String budget;

    @Column(name = "remark")
    private String remark;

    // Many tenants to one user
    @JsonIgnore
    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // One tenant to many properties
    @JsonIgnore
    @OneToMany(mappedBy = "tenant", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TenantProperty> tenantProperties;

    @ManyToMany
    @JoinTable(name = "tenant_property", joinColumns = @JoinColumn(name = "tenant_id"), inverseJoinColumns = @JoinColumn(name = "property_id"))
    private List<Property> property;
}
