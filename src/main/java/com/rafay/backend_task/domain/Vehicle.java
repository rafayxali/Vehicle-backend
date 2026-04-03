package com.rafay.backend_task.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
public class Vehicle {

    public enum Status {
        AVAILABLE, SOLD
    }

    @Id
    @GeneratedValue
    private UUID id;

    private String tenantId;

    @ManyToOne
    @JoinColumn(name = "dealer_id")
    private Dealer dealer;

    private String model;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Status status;

    // Getters & Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getTenantId() { return tenantId; }
    public void setTenantId(String tenantId) { this.tenantId = tenantId; }

    public Dealer getDealer() { return dealer; }
    public void setDealer(Dealer dealer) { this.dealer = dealer; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}