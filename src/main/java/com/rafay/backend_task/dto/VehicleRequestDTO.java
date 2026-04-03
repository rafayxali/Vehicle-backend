package com.rafay.backend_task.dto;

import com.rafay.backend_task.domain.Vehicle.Status;
import java.math.BigDecimal;
import java.util.UUID;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class VehicleRequestDTO {
    @NotNull
    private UUID dealerId;

    @NotBlank(message = "Model required")
    private String model;

    @NotNull(message = "Price required")
    @Positive(message = "Price must be positive")
    private BigDecimal price;

    @NotNull(message = "Status required")
    private Status status;

    // Getters & Setters
    public UUID getDealerId() { return dealerId; }
    public void setDealerId(UUID dealerId) { this.dealerId = dealerId; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public Status getStatus() { return status; }
    public void setStatus(Status status) { this.status = status; }
}