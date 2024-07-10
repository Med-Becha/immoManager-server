package com.asm.immoManager.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.asm.immoManager.entity.Tenant;

public interface TenantRepository extends JpaRepository<Tenant, Long> {
    List<Tenant> findAllByUserId(Long userId);
}