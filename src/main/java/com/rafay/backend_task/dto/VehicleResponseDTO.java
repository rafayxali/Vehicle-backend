package com.rafay.backend_task.dto;

import com.rafay.backend_task.domain.Vehicle.Status;
import java.math.BigDecimal;
import java.util.UUID;

public class VehicleResponseDTO {
    private UUID id;
    private UUID dealerId;
    private String model;
    private BigDecimal price;
    private Status status;

    // Getters & Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public UUID getDealerId() { return dealerId; }
    public void setDealerId(UUID dealerId) { this.dealerId = dealerId; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}