package com.asm.immoManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.asm.immoManager.entity.TenantProperty;

public interface TenantPropertyRepository extends JpaRepository<TenantProperty, Long> {
}