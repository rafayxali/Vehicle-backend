package com.rafay.backend_task.service;

import com.rafay.backend_task.domain.Dealer;
import com.rafay.backend_task.domain.Vehicle;
import com.rafay.backend_task.dto.VehicleRequestDTO;
import com.rafay.backend_task.dto.VehicleResponseDTO;
import com.rafay.backend_task.repository.DealerRepository;
import com.rafay.backend_task.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private DealerRepository dealerRepository;

    // CREATE
    public VehicleResponseDTO createVehicle(String tenantId, VehicleRequestDTO dto) {
        Dealer dealer = dealerRepository.findById(dto.getDealerId())
                .filter(d -> d.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("Dealer not found or forbidden"));

        Vehicle v = new Vehicle();
        v.setTenantId(tenantId);
        v.setDealer(dealer);
        v.setModel(dto.getModel());
        v.setPrice(dto.getPrice());
        v.setStatus(dto.getStatus());

        return mapToDTO(vehicleRepository.save(v));
    }

    // GET BY ID
    public VehicleResponseDTO getVehicleById(String tenantId, UUID id) {
        Vehicle v = vehicleRepository.findById(id)
                .filter(x -> x.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("Vehicle not found or forbidden"));

        return mapToDTO(v);
    }

    // PAGINATED + FILTER
    public Page<VehicleResponseDTO> getVehicles(
            String tenantId,
            int page,
            int size,
            String sortBy,
            String direction,
            String subscription
    ) {

        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Vehicle> result;

        if ("PREMIUM".equalsIgnoreCase(subscription)) {
            result = vehicleRepository
                    .findByDealer_TenantIdAndDealer_SubscriptionType(
                            tenantId,
                            Dealer.SubscriptionType.PREMIUM,
                            pageable
                    );
        } else {
            result = vehicleRepository.findByTenantId(tenantId, pageable);
        }

        return result.map(this::mapToDTO);
    }

    // UPDATE
    public VehicleResponseDTO updateVehicle(String tenantId, UUID id, VehicleRequestDTO dto) {

        Vehicle v = vehicleRepository.findById(id)
                .filter(x -> x.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("Vehicle not found or forbidden"));

        Dealer dealer = dealerRepository.findById(dto.getDealerId())
                .filter(d -> d.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("Dealer not found or forbidden"));

        v.setDealer(dealer);
        v.setModel(dto.getModel());
        v.setPrice(dto.getPrice());
        v.setStatus(dto.getStatus());

        return mapToDTO(vehicleRepository.save(v));
    }

    // DELETE
    public void deleteVehicle(String tenantId, UUID id) {
        Vehicle v = vehicleRepository.findById(id)
                .filter(x -> x.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("Vehicle not found or forbidden"));

        vehicleRepository.delete(v);
    }

    // MAPPER
    private VehicleResponseDTO mapToDTO(Vehicle v) {
        VehicleResponseDTO dto = new VehicleResponseDTO();
        dto.setId(v.getId());
        dto.setDealerId(v.getDealer().getId());
        dto.setModel(v.getModel());
        dto.setPrice(v.getPrice());
        dto.setStatus(v.getStatus());
        return dto;
    }
}