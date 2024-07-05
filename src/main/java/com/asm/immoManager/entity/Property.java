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
    @Column(name = "property_id")
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

    @NotBlank(message = "Sizes are required.")
    @Column(name = "sizes", nullable = false)
    private String sizes;

    @NotBlank(message = "Locations are required.")
    @Column(name = "location", nullable = false)
    private String locations;

    @NotBlank(message = "Equipment is required.")
    @Column(name = "equipment", nullable = false)
    private String equipment;

    @NotBlank(message = "Price is required.")
    @Column(name = "price", nullable = false)
    private String price;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDate createdAt;

    @Enumerated(EnumType.STRING)
    @Column(name = "occupation_status", nullable = false)
    private OccupationStatus occupationStatus;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDate.now();
    }

    public enum OccupationStatus {
        OCCUPEES("sont occupées"),
        DISPONIBLES("disponibles"),
        EN_COURS_DE_MAINTENANCE("en cours de maintenance");

        private final String displayValue;

        OccupationStatus(String displayValue) {
            this.displayValue = displayValue;
        }

        public String getDisplayValue() {
            return displayValue;
        }
    }
}
