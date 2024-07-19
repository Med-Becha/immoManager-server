package com.asm.immoManager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asm.immoManager.entity.Tenant;
import com.asm.immoManager.service.TenantService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/tenant")
@AllArgsConstructor
public class TenantController {

    TenantService tenantService;

    // user create new tenant
    @PostMapping("/user/{userid}")
    public ResponseEntity<Tenant> saveTenant(@RequestBody Tenant tenant, @PathVariable Long userid) {
        return new ResponseEntity<>(tenantService.saveTenant(tenant, userid), HttpStatus.CREATED);
    }

    // get tenant by id
    @GetMapping("/{id}")
    public ResponseEntity<Tenant> getTenant(@PathVariable Long id) {
        return new ResponseEntity<>(tenantService.getTenantById(id), HttpStatus.OK);
    }

    // get tenants
    @GetMapping("/all")
    public ResponseEntity<List<Tenant>> getTenants() {
        // return new ResponseEntity<>(tenantService.getAllTenants(), HttpStatus.OK);
        return ResponseEntity.status(HttpStatus.OK).body(tenantService.getAllTenants());
    }

    // get user tenants
    @GetMapping("/all/{userId}")
    public ResponseEntity<List<Tenant>> getUserTenants(@PathVariable Long userId) {
        return new ResponseEntity<>(tenantService.getUserTenants(userId), HttpStatus.OK);
    }

    // update a tenant
    @PatchMapping("/{id}")
    ResponseEntity<String> updateTenant(@PathVariable Long id, @RequestBody Tenant tenant) {

        Tenant updatedTenant = tenantService.updateTenant(id, tenant);
        if (updatedTenant == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok("Tenant updated successfully");

    }

}
