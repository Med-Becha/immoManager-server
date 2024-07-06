package com.asm.immoManager.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.asm.immoManager.entity.Property;
import com.asm.immoManager.service.PropertyService;

@RestController
@RequestMapping("/property")
public class PropertyController {

    @Autowired
    PropertyService propertyService;

    // user create new porperty
    @PostMapping("/user/{userid}")
    public ResponseEntity<Property> saveProperty(@RequestBody Property property, @PathVariable Long userid) {
        return new ResponseEntity<>(propertyService.saveProperty(property, userid), HttpStatus.CREATED);
    }

    // get user properties by user id
    @GetMapping("/userproperties/{userId}")
    public ResponseEntity<List<Property>> getUserProperties(@PathVariable Long userId) {
        return new ResponseEntity<List<Property>>(propertyService.getUserProperties(userId), HttpStatus.OK);
    }

    // get property by id
    @GetMapping("/{propertyId}")
    public ResponseEntity<Property> getUserPropert(@PathVariable Long propertyId) {
        return new ResponseEntity<Property>(propertyService.getPropertyById(propertyId), HttpStatus.OK);
    }
}