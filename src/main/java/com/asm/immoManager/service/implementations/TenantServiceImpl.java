package com.asm.immoManager.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.asm.immoManager.entity.Property;
import com.asm.immoManager.entity.Property.PropertyStatus;
import com.asm.immoManager.entity.Tenant;
import com.asm.immoManager.entity.TenantProperty;
import com.asm.immoManager.entity.User;
import com.asm.immoManager.repository.PropertyRepository;
import com.asm.immoManager.repository.TenantPropertyRepository;
import com.asm.immoManager.repository.TenantRepository;
import com.asm.immoManager.repository.UserRepository;
import com.asm.immoManager.service.TenantService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TenantServiceImpl implements TenantService {

    TenantRepository tenantRepository;
    UserRepository userRepository;
    PropertyRepository propertyRepository;
    TenantPropertyRepository tenantPropertyRepository;

    // new tenant
    @Override
    public Tenant saveTenant(Tenant tenant, Long userId) {
        User user = userRepository.findById(userId).get();
        tenant.setUser(user);

        return tenantRepository.save(tenant);
    }

    // delete tenant
    @Override
    public void deleteTenantById(Long id) {
        tenantRepository.deleteById(id);
    }

    // get tenant by id
    @Override
    public Tenant getTenantById(Long id) {
        return tenantRepository.findById(id).get();
    }

    // get all tenant
    @Override
    public List<Tenant> getAllTenants() {
        return (List<Tenant>) tenantRepository.findAll();
    }

    // get user tenats
    @Override
    public List<Tenant> getUserTenants(Long id) {
        return (List<Tenant>) tenantRepository.findAllByUserId(id);
    }

    @Override
    public Tenant updateTenant(Long id, Tenant tenantDetails) {
        return tenantRepository.findById(id).map(tenant -> {
            if (tenantDetails.getName() != null) {
                tenant.setName(tenantDetails.getName());
            }
            if (tenantDetails.getEmail() != null) {
                tenant.setEmail(tenantDetails.getEmail());
            }
            if (tenantDetails.getPhone() != null) {
                tenant.setPhone(tenantDetails.getPhone());
            }
            if (tenantDetails.getCin() != null) {
                tenant.setCin(tenantDetails.getCin());
            }
            if (tenantDetails.getAddress() != null) {
                tenant.setAddress(tenantDetails.getAddress());
            }
            if (tenantDetails.getJob() != null) {
                tenant.setJob(tenantDetails.getJob());
            }
            if (tenantDetails.getBudget() != null) {
                tenant.setBudget(tenantDetails.getBudget());
            }
            if (tenantDetails.getRemark() != null) {
                tenant.setRemark(tenantDetails.getRemark());
            }
            return tenantRepository.save(tenant);
        }).orElse(null); // Return null if tenant not found
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
