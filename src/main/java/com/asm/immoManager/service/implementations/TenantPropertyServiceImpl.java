package com.asm.immoManager.service.implementations;

import org.springframework.stereotype.Service;

import com.asm.immoManager.entity.TenantProperty;
import com.asm.immoManager.repository.TenantPropertyRepository;
import com.asm.immoManager.service.TenantPropertyService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TenantPropertyServiceImpl implements TenantPropertyService {

    TenantPropertyRepository tenantPropertyRepository;

    public TenantProperty assignPropertyToTenant(TenantProperty tenantProperty) {
        return tenantPropertyRepository.save(tenantProperty);
    }
}
