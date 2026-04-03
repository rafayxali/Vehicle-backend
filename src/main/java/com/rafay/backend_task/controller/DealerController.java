package com.rafay.backend_task.controller;

import com.rafay.backend_task.dto.DealerRequestDTO;
import com.rafay.backend_task.dto.DealerResponseDTO;
import com.rafay.backend_task.service.DealerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/dealers")
public class DealerController {

    @Autowired
    private DealerService dealerService;

    // create dealer
    @PostMapping
    public ResponseEntity<DealerResponseDTO> createDealer(
            @RequestHeader("X-Tenant-Id") String tenantId,
            @Valid @RequestBody DealerRequestDTO dto) {

        if (tenantId == null || tenantId.isBlank())
            return ResponseEntity.badRequest().build();

        return ResponseEntity.ok(dealerService.createDealer(tenantId, dto));
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<DealerResponseDTO> getDealer(
            @RequestHeader("X-Tenant-Id") String tenantId,
            @PathVariable UUID id) {

        return ResponseEntity.ok(dealerService.getDealerById(tenantId, id));
    }

    // GET ALL (PAGINATED)
    @GetMapping
    public ResponseEntity<Page<DealerResponseDTO>> getDealers(
            @RequestHeader("X-Tenant-Id") String tenantId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "name") String sortBy,
            @RequestParam(defaultValue = "asc") String direction) {

        return ResponseEntity.ok(
                dealerService.getDealers(tenantId, page, size, sortBy, direction)
        );
    }

    // UPDATE
    @PatchMapping("/{id}")
    public ResponseEntity<DealerResponseDTO> updateDealer(
            @RequestHeader("X-Tenant-Id") String tenantId,
            @PathVariable UUID id,
            @Valid @RequestBody DealerRequestDTO dto) {

        return ResponseEntity.ok(
                dealerService.updateDealer(tenantId, id, dto)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDealer(
            @RequestHeader("X-Tenant-Id") String tenantId,
            @PathVariable UUID id) {

        dealerService.deleteDealer(tenantId, id);
        return ResponseEntity.noContent().build();
    }
}