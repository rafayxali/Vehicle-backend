package com.rafay.backend_task.controller;

import com.rafay.backend_task.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/dealers/countBySubscription")
    public ResponseEntity<Map<String, Long>> countDealers(
            @RequestHeader("X-Tenant-Id") String tenantId,
            @RequestHeader("X-User-Role") String role) {

        if (!"GLOBAL_ADMIN".equalsIgnoreCase(role)) {
            return ResponseEntity.status(403).build();
        }

        return ResponseEntity.ok(
                adminService.countBySubscription(tenantId)
        );
    }
}