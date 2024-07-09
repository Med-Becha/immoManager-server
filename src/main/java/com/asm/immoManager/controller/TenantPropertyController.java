package com.asm.immoManager.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.asm.immoManager.entity.TenantProperty;
import com.asm.immoManager.service.TenantPropertyService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RestController
@RequestMapping("/tenantproperties")
public class TenantPropertyController {

    TenantPropertyService tenantPropertyService;

    @PostMapping
    public ResponseEntity<TenantProperty> assignPropertyToTenant(@RequestBody TenantProperty tenantProperty) {
        TenantProperty createdTenantProperty = tenantPropertyService.assignPropertyToTenant(tenantProperty);
        return new ResponseEntity<>(createdTenantProperty, HttpStatus.CREATED);
    }
}
