package com.asm.immoManager.service.implementations;

import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import com.asm.immoManager.entity.Property;
import com.asm.immoManager.entity.TenantProperty;
import com.asm.immoManager.entity.User;
import com.asm.immoManager.repository.PropertyRepository;
import com.asm.immoManager.repository.TenantPropertyRepository;
import com.asm.immoManager.repository.TenantRepository;
import com.asm.immoManager.repository.UserRepository;
import com.asm.immoManager.service.PropertyService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PropertyServiceImpl implements PropertyService {

    PropertyRepository propertyRepository;
    UserRepository userRepository;
    TenantRepository tenantRepository;
    TenantPropertyRepository tenantPropertyRepository;

    @Override
    public Property saveProperty(Property property, Long userId) {
        User user = userRepository.findById(userId).get();
        property.setUser(user);

        return propertyRepository.save(property);
    }

    // get Property by id
    @Override
    public Property getPropertyById(Long id) {
        return propertyRepository.findById(id).get();
    }

    // delete Property by id
    @Override
    public void deletePropertyById(Long id) {
        propertyRepository.deleteById(id);
    }

    // get all users properties
    @Override
    public List<Property> getAllProperties() {
        return (List<Property>) propertyRepository.findAll();
    }

    // get user properties
    @Override
    public List<Property> getUserProperties(Long userId) {
        return (List<Property>) propertyRepository.findByUserId(userId);
    }

    // update a property
    public Property updateProperty(Long id, Property propertyDetails) {
        return propertyRepository.findById(id).map(property -> {
            if (propertyDetails.getName() != null) {
                property.setName(propertyDetails.getName());
            }
            if (propertyDetails.getDescription() != null) {
                property.setDescription(propertyDetails.getDescription());
            }
            if (propertyDetails.getDetails() != null) {
                property.setDetails(propertyDetails.getDetails());
            }
            if (propertyDetails.getSizes() != null) {
                property.setSizes(propertyDetails.getSizes());
            }
            if (propertyDetails.getLocation() != null) {
                property.setLocation(propertyDetails.getLocation());
            }
            if (propertyDetails.getEquipment() != null) {
                property.setEquipment(propertyDetails.getEquipment());
            }
            if (propertyDetails.getPrice() != null) {
                property.setPrice(propertyDetails.getPrice());
            }
            if (propertyDetails.getStatus() != null) {
                property.setStatus(propertyDetails.getStatus());
            }
            return propertyRepository.save(property);
        }).orElse(null); // Return null if property not found
    }

    //
    public TenantProperty addTenantProperty(Long propertyId, Long tenantId, LocalDate startDate, LocalDate endDate) {
        TenantProperty tenantProperty = new TenantProperty();
        tenantProperty.setProperty(propertyRepository.findById(propertyId).orElseThrow());
        tenantProperty.setTenant(tenantRepository.findById(tenantId).orElseThrow());
        tenantProperty.setStartDate(startDate);
        tenantProperty.setEndDate(endDate);
        return tenantPropertyRepository.save(tenantProperty);
    }

}