package com.rafay.backend_task.repository;

import com.rafay.backend_task.domain.Vehicle;
import com.rafay.backend_task.domain.Dealer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


import java.util.List;
import java.util.UUID;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {
   
    Page<Vehicle> findByTenantId(String tenantId, Pageable pageable);
    Page<Vehicle> findByDealer_TenantIdAndDealer_SubscriptionType(String tenantId, Dealer.SubscriptionType subscriptionType, Pageable pageable);
}