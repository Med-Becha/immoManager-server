package com.asm.immoManager.service;

import java.util.Date;
import java.util.List;

import com.asm.immoManager.entity.Property;
import com.asm.immoManager.entity.TenantProperty;

public interface PropertyService {

    // create property
    Property saveProperty(Property property, Long userId);

    // get property
    Property getPropertyById(Long id);

    // delete property
    void deletePropertyById(Long id);

    // get all users properties
    List<Property> getAllProperties();

    // update a property by user id
    Property updateProperty(Long id, Property property);

    // get user properties
    List<Property> getUserProperties(Long id);

    TenantProperty addTenantProperty(Long propertyId, Long tenantId, Date startDate, Date endDate);

}