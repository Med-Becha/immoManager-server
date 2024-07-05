package com.asm.immoManager.repository;

import org.springframework.data.repository.CrudRepository;

import com.asm.immoManager.entity.Tenant;

public interface PropertyRepository extends CrudRepository<Tenant, Long> {

}