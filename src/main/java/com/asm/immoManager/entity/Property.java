package com.asm.immoManager.entity;

import java.time.LocalDate;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;

@Entity
@Table(name = "properties")
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
    private String descriptions;

    @NotBlank(message = "Details are required, exp S+2.")
    @Column(name = "details")
    private String details;

    @NotBlank(message = "Sizes are required. exp 90m²")
    @Column(name = "sizes", nullable = false)
    private String sizes;

    @NotBlank(message = "Locations are required. exp Gremda.")
    @Column(name = "location", nullable = false)
    private String locations;

    @NotBlank(message = "Equipment is required.")
    @Column(name = "equipment")
    private String equipment;

    @NotBlank(message = "Price is required.")
    @Column(name = "price", nullable = false)
    private String price;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;

    @NotBlank(message = "Status is required.")
    @Column(name = "ocupation_status", nullable = false)
    private String status;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
