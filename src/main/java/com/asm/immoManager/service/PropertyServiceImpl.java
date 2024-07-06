package com.asm.immoManager.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.asm.immoManager.entity.Property;
import com.asm.immoManager.entity.User;
import com.asm.immoManager.repository.PropertyRepository;
import com.asm.immoManager.repository.UserRepository;

@Service
public class PropertyServiceImpl implements PropertyService {

    @Autowired
    PropertyRepository propertyRepository;

    @Autowired
    UserRepository userRepository;

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

}
