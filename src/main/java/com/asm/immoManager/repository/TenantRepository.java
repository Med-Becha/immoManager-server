package com.asm.immoManager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.asm.immoManager.entity.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, Long> {

}