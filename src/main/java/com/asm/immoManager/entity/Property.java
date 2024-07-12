package com.asm.immoManager.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "property")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Property {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required.")
    @Column(name = "name", nullable = false)
    private String name;

    @NotBlank(message = "Description is required.")
    @Column(name = "description", nullable = false)
    private String description;

    @NotBlank(message = "Details are required, exp S+2.")
    @Column(name = "details")
    private String details;

    @NotBlank(message = "Sizes are required. exp 90m²")
    @Column(name = "sizes", nullable = false)
    private String sizes;

    @NotBlank(message = "Locations are required. exp Gremda.")
    @Column(name = "location", nullable = false)
    private String location;

    @NotBlank(message = "Equipment is required.")
    @Column(name = "equipment")
    private String equipment;

    @NotBlank(message = "Price is required.")
    @Column(name = "price", nullable = false)
    private String price;

    // @NotBlank(message = "Status is required.")
    @Enumerated(EnumType.STRING)
    @Column(name = "ocupation_status", nullable = false)
    private PropertyStatus status;

    // Many properties to one user
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    // many properties to many tenants
    @JsonIgnore
    @ManyToMany(mappedBy = "property")
    private List<Tenant> tenants;

    // Bidirectional relationship with TenantProperty
    // @JsonIgnore
    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<TenantProperty> tenantProperties;

    // one property to many images

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
    private List<PropertyImages> images;

    public enum PropertyStatus {
        disponibles,
        occupées,
        maintenance
    }
}
