package com.rafay.backend_task.dto;

import com.rafay.backend_task.domain.Dealer.SubscriptionType;
import jakarta.validation.constraints.*;

public class DealerRequestDTO {

    @NotBlank(message = "Name required")
    private String name;

    @NotBlank(message = "Email required")
    @Email(message = "Invalid email format")
    private String email;

    @NotNull(message = "Subscription type required")
    private SubscriptionType subscriptionType;

    // Getters & Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public SubscriptionType getSubscriptionType() { return subscriptionType; }
    public void setSubscriptionType(SubscriptionType subscriptionType) { this.subscriptionType = subscriptionType; }
}