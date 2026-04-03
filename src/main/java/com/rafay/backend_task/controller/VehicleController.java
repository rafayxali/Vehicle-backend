package com.rafay.backend_task.controller;

import com.rafay.backend_task.dto.VehicleRequestDTO;
import com.rafay.backend_task.dto.VehicleResponseDTO;
import com.rafay.backend_task.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/vehicles")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    // Create vehicle
    @PostMapping
    public ResponseEntity<VehicleResponseDTO> createVehicle(
            @RequestHeader("X-Tenant-Id") String tenantId,
            @RequestBody VehicleRequestDTO dto) {

        if (tenantId == null || tenantId.isBlank())
            return ResponseEntity.badRequest().build();

        VehicleResponseDTO created = vehicleService.createVehicle(tenantId, dto);
        return ResponseEntity.ok(created);
    }

    // Get vehicle by ID
    @GetMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> getVehicleById(
            @RequestHeader("X-Tenant-Id") String tenantId,
            @PathVariable UUID id) {

        VehicleResponseDTO dto = vehicleService.getVehicleById(tenantId, id);
        return ResponseEntity.ok(dto);
    }

    // Get all vehicles (optional filters)
   // Replace the getVehicles method in VehicleController.java with this:
@GetMapping
public ResponseEntity<Page<VehicleResponseDTO>> getVehicles(
        @RequestHeader("X-Tenant-Id") String tenantId,
        @RequestParam(defaultValue = "0") int page,
        @RequestParam(defaultValue = "10") int size,
        @RequestParam(defaultValue = "model") String sortBy,
        @RequestParam(defaultValue = "asc") String direction,
        @RequestParam(required = false) String subscription) {

    // Call the single method that handles both logic paths
    return ResponseEntity.ok(
            vehicleService.getVehicles(tenantId, page, size, sortBy, direction, subscription)
    );
}

    // Update vehicle
    @PatchMapping("/{id}")
    public ResponseEntity<VehicleResponseDTO> updateVehicle(
            @RequestHeader("X-Tenant-Id") String tenantId,
            @PathVariable UUID id,
            @RequestBody VehicleRequestDTO dto) {

        VehicleResponseDTO updated = vehicleService.updateVehicle(tenantId, id, dto);
        return ResponseEntity.ok(updated);
    }

    // Delete vehicle
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicle(
            @RequestHeader("X-Tenant-Id") String tenantId,
            @PathVariable UUID id) {

        vehicleService.deleteVehicle(tenantId, id);
        return ResponseEntity.noContent().build();
    }
}