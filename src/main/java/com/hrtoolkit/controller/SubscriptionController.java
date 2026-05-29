package com.hrtoolkit.controller;

import com.hrtoolkit.entity.Subscription;
import com.hrtoolkit.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/subscriptions")
@CrossOrigin(origins = "*")
public class SubscriptionController {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @GetMapping
    public ResponseEntity<List<Subscription>> getAllSubscriptions() {
        return ResponseEntity.ok(subscriptionRepository.findAll());
    }

    @GetMapping("/active")
    public ResponseEntity<List<Subscription>> getActiveSubscriptions() {
        return ResponseEntity.ok(subscriptionRepository.findByIsActiveTrue());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Subscription> getSubscriptionById(@PathVariable Long id) {
        Optional<Subscription> subscription = subscriptionRepository.findById(id);
        return subscription.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Subscription> createSubscription(@RequestBody Subscription subscription) {
        Subscription savedSubscription = subscriptionRepository.save(subscription);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSubscription);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Subscription> updateSubscription(@PathVariable Long id, @RequestBody Subscription subscriptionDetails) {
        Optional<Subscription> subscription = subscriptionRepository.findById(id);
        if (subscription.isPresent()) {
            Subscription existingSubscription = subscription.get();
            existingSubscription.setPlan(subscriptionDetails.getPlan());
            existingSubscription.setDescription(subscriptionDetails.getDescription());
            existingSubscription.setPrice(subscriptionDetails.getPrice());
            existingSubscription.setDurationMonths(subscriptionDetails.getDurationMonths());
            existingSubscription.setIsActive(subscriptionDetails.getIsActive());
            return ResponseEntity.ok(subscriptionRepository.save(existingSubscription));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubscription(@PathVariable Long id) {
        if (subscriptionRepository.existsById(id)) {
            subscriptionRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
