package com.rafay.backend_task.repository;

import com.rafay.backend_task.domain.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, UUID> {

    Page<Dealer> findByTenantId(String tenantId, Pageable pageable);
}