package com.asm.immoManager.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.asm.immoManager.entity.Property;
import com.asm.immoManager.entity.Tenant;
import com.asm.immoManager.entity.User;
import com.asm.immoManager.repository.TenantRepository;
import com.asm.immoManager.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TenantServiceImpl implements TenantService {

    TenantRepository tenantRepository;
    UserRepository userRepository;

    @Override
    public Tenant saveTenant(Tenant tenant, Long userId) {
        User user = userRepository.findById(userId).get();
        tenant.setUser(user);

        return tenantRepository.save(tenant);
    }

    @Override
    public void deleteTenantById(Long id) {
        tenantRepository.deleteById(id);
    }

    @Override
    public Tenant getTenantById(Long id) {
        return tenantRepository.findById(id).get();
    }

    @Override
    public List<Tenant> getAllTenants() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAllTenants'");
    }

    @Override
    public List<Tenant> getUserTenants(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getUserTenants'");
    }
}
