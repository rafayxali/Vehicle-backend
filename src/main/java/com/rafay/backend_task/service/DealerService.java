package com.rafay.backend_task.service;

import com.rafay.backend_task.domain.Dealer;
import com.rafay.backend_task.dto.DealerRequestDTO;
import com.rafay.backend_task.dto.DealerResponseDTO;
import com.rafay.backend_task.repository.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DealerService {

    @Autowired
    private DealerRepository dealerRepository;

    // CREATE
    public DealerResponseDTO createDealer(String tenantId, DealerRequestDTO dto) {
        Dealer dealer = new Dealer();
        dealer.setTenantId(tenantId);
        dealer.setName(dto.getName());
        dealer.setEmail(dto.getEmail());
        dealer.setSubscriptionType(dto.getSubscriptionType());

        return mapToDTO(dealerRepository.save(dealer));
    }

    // GET BY ID (tenant enforced)
    public DealerResponseDTO getDealerById(String tenantId, UUID id) {
        Dealer dealer = dealerRepository.findById(id)
                .filter(d -> d.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("Dealer not found or forbidden"));

        return mapToDTO(dealer);
    }

    // PAGINATED GET
    public Page<DealerResponseDTO> getDealers(String tenantId, int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return dealerRepository.findByTenantId(tenantId, pageable)
                .map(this::mapToDTO);
    }

    // UPDATE
    public DealerResponseDTO updateDealer(String tenantId, UUID id, DealerRequestDTO dto) {
        Dealer dealer = dealerRepository.findById(id)
                .filter(d -> d.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("Dealer not found or forbidden"));

        dealer.setName(dto.getName());
        dealer.setEmail(dto.getEmail());
        dealer.setSubscriptionType(dto.getSubscriptionType());

        return mapToDTO(dealerRepository.save(dealer));
    }

    // DELETE
    public void deleteDealer(String tenantId, UUID id) {
        Dealer dealer = dealerRepository.findById(id)
                .filter(d -> d.getTenantId().equals(tenantId))
                .orElseThrow(() -> new RuntimeException("Dealer not found or forbidden"));

        dealerRepository.delete(dealer);
    }

    // MAPPER
    private DealerResponseDTO mapToDTO(Dealer d) {
        DealerResponseDTO dto = new DealerResponseDTO();
        dto.setId(d.getId());
        dto.setName(d.getName());
        dto.setEmail(d.getEmail());
        dto.setSubscriptionType(d.getSubscriptionType());
        return dto;
    }
}