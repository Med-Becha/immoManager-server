package com.asm.immoManager.controller;

import java.util.List;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import com.asm.immoManager.entity.Property;
import com.asm.immoManager.service.PropertyService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("/property")
public class PropertyController {

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
    public ResponseEntity<Property> getUserProperty(@PathVariable Long propertyId) {
        return new ResponseEntity<Property>(propertyService.getPropertyById(propertyId), HttpStatus.OK);
    }

    // delete property by id
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProperty(@PathVariable Long id) {
        propertyService.deletePropertyById(id);
        return new ResponseEntity<>("Property deleted successfully", HttpStatus.OK);
    }

    // get all properties
    @GetMapping("/all")
    public ResponseEntity<List<Property>> getProperties() {
        return new ResponseEntity<List<Property>>(propertyService.getAllProperties(), HttpStatus.OK);
    }

    // update a property
    @PatchMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody Property property) {
        Property updatedProperty = propertyService.updateProperty(id, property);
        if (updatedProperty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Property updated successfully");
    }

    // @PostMapping("/{propertyId}/tenants/{tenantId}")
    // public ResponseEntity<TenantProperty> addTenantProperty(
    // @PathVariable Long propertyId,
    // @PathVariable Long tenantId,
    // @RequestParam LocalDate startDate,
    // @RequestParam LocalDate endDate) {
    // TenantProperty tenantProperty = propertyService.addTenantProperty(propertyId,
    // tenantId, startDate, endDate);
    // return new ResponseEntity<>(tenantProperty, HttpStatus.CREATED);
    // }
}
