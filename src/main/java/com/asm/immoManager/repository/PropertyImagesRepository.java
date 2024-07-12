package com.asm.immoManager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asm.immoManager.entity.PropertyImages;

@Repository
public interface PropertyImagesRepository extends JpaRepository<PropertyImages, Long> {
    Optional<PropertyImages> findByName(String name);

    List<PropertyImages> findByPropertyId(Long propertyId);
}
