package com.rafay.backend_task.service;

import com.rafay.backend_task.domain.Dealer;
import com.rafay.backend_task.repository.DealerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Example;

import java.util.HashMap;
import java.util.Map;

@Service
public class AdminService {

    @Autowired
    private DealerRepository dealerRepository;

    public Map<String, Long> countBySubscription(String tenantId) {
        // Fix: Using Example instead of the Lambda that was causing errors
        Dealer basicProbe = new Dealer();
        basicProbe.setTenantId(tenantId);
        basicProbe.setSubscriptionType(Dealer.SubscriptionType.BASIC);

        Dealer premiumProbe = new Dealer();
        premiumProbe.setTenantId(tenantId);
        premiumProbe.setSubscriptionType(Dealer.SubscriptionType.PREMIUM);

        long basic = dealerRepository.count(Example.of(basicProbe));
        long premium = dealerRepository.count(Example.of(premiumProbe));

        Map<String, Long> result = new HashMap<>();
        result.put("BASIC", basic);
        result.put("PREMIUM", premium);

        return result;
    }
}