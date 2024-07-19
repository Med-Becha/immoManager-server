package com.asm.immoManager.service;

import com.asm.immoManager.entity.TenantProperty;

public interface TenantPropertyService {

    TenantProperty assignPropertyToTenant(TenantProperty tenantProperty);

    // set tenant to a properties
    TenantProperty setTenantToProperty(Long tenantId, Long propertyId, TenantProperty tenantProperty);
}