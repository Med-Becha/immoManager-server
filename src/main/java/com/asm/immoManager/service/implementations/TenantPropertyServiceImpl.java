package com.asm.immoManager.service.implementations;

import org.springframework.stereotype.Service;

import com.asm.immoManager.entity.Property;
import com.asm.immoManager.entity.Property.PropertyStatus;
import com.asm.immoManager.entity.Tenant;
import com.asm.immoManager.entity.TenantProperty;
import com.asm.immoManager.repository.PropertyRepository;
import com.asm.immoManager.repository.TenantPropertyRepository;
import com.asm.immoManager.repository.TenantRepository;
import com.asm.immoManager.service.TenantPropertyService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class TenantPropertyServiceImpl implements TenantPropertyService {

    TenantPropertyRepository tenantPropertyRepository;
    PropertyRepository propertyRepository;
    TenantRepository tenantRepository;

    public TenantProperty assignPropertyToTenant(TenantProperty tenantProperty) {
        return tenantPropertyRepository.save(tenantProperty);
    }

    // add a property to a tenant with tenant property
    @Override
    public TenantProperty setTenantToProperty(Long tenantId, Long propertyId, TenantProperty tenantProperty) {
        // Fetch the property
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found with id " + propertyId));

        // Fetch the tenant
        Tenant tenant = tenantRepository.findById(tenantId)
                .orElseThrow(() -> new RuntimeException("Tenant not found with id " + tenantId));

        if (property.getStatus() == Property.PropertyStatus.occupées) {
            throw new RuntimeException("Property already belongs to a tenant");
        }
        // Set tenant and property
        tenantProperty.setTenant(tenant);
        tenantProperty.setProperty(property);

        // Set start date and end date
        tenantProperty.setStartDate(tenantProperty.getStartDate());
        tenantProperty.setEndDate(tenantProperty.getEndDate());

        // set the property status occupées
        property.setStatus(PropertyStatus.occupées);
        propertyRepository.save(property);
        // Save tenant property
        return tenantPropertyRepository.save(tenantProperty);
    }
}
