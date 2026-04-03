package com.rafay.backend_task.dto;

import com.rafay.backend_task.domain.Dealer.SubscriptionType;
import java.util.UUID;

public class DealerResponseDTO {
    private UUID id;
    private String name;
    private String email;
    private SubscriptionType subscriptionType;

    // Getters & Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public SubscriptionType getSubscriptionType() { return subscriptionType; }
    public void setSubscriptionType(SubscriptionType subscriptionType) { this.subscriptionType = subscriptionType; }
}