package com.asm.immoManager.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.asm.immoManager.entity.Property;

public interface PropertyRepository extends CrudRepository<Property, Long> {

    List<Property> findByUserId(Long userId);
}