package com.asm.immoManager.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asm.immoManager.service.TenantService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/tenant")
@AllArgsConstructor
public class TenantController {

    TenantService tenantService;
}
