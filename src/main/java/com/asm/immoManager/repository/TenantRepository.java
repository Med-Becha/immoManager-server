package com.asm.immoManager.repository;

import org.springframework.data.repository.CrudRepository;

import com.asm.immoManager.entity.Tenant;

public interface TenantRepository extends CrudRepository<Tenant, Long> {

}