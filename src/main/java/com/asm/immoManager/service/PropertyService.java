package com.asm.immoManager.service;

import java.util.List;

import com.asm.immoManager.entity.Property;

public interface PropertyService {

    // create property
    Property saveProperty(Property property, Long userId);

    // get property
    Property getPropertyById(Long id);

    // delete property
    void deletePropertyById(Long id);

    // get all users properties
    List<Property> getAllProperties();

    // get user properties
    List<Property> getUserProperties(Long id);

}