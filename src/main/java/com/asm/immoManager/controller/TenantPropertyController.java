package com.asm.immoManager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.asm.immoManager.entity.TenantProperty;
import com.asm.immoManager.service.TenantPropertyService;
import com.asm.immoManager.service.TenantService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/tenantproperty")
public class TenantPropertyController {

    TenantPropertyService tenantPropertyService;
    TenantService tenantService;

    // set a tenant to a property
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/{tenatId}/{propertyId}")
    ResponseEntity<String> updateTenant(@PathVariable Long tenatId, @PathVariable Long propertyId,
            @RequestBody TenantProperty tenantProperty) {

        TenantProperty setTenantToProperty = tenantPropertyService.setTenantToProperty(tenatId, propertyId,
                tenantProperty);
        if (setTenantToProperty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Tenant has a new property successfully");

    }

    @PostMapping
    public ResponseEntity<TenantProperty> assignPropertyToTenant(@RequestBody TenantProperty tenantProperty) {
        TenantProperty createdTenantProperty = tenantPropertyService.assignPropertyToTenant(tenantProperty);
        return new ResponseEntity<>(createdTenantProperty, HttpStatus.CREATED);
    }
}
