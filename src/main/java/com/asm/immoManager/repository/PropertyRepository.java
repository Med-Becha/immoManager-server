package com.asm.immoManager.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.asm.immoManager.entity.Property;

public interface PropertyRepository extends JpaRepository<Property, Long> {

    List<Property> findByUserId(Long userId);
}