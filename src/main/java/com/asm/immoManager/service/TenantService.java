package com.asm.immoManager.service;

import java.util.List;
import com.asm.immoManager.entity.Tenant;

public interface TenantService {

    // create tenant
    Tenant saveTenant(Tenant tenant, Long userId);

    // get tenant
    Tenant getTenantById(Long id);

    // delete property
    void deleteTenantById(Long id);

    // get all users properties
    List<Tenant> getAllTenants();

    // get user properties
    List<Tenant> getUserTenants(Long id);

    // update a tenant
    Tenant updateTenant(Long id, Tenant tenant);

}