package mall.shopping.mall.controller;

import lombok.RequiredArgsConstructor;
import mall.shopping.mall.service.subscription.SubscriptionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/subscription")
@RequiredArgsConstructor
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    @PostMapping("/{userId}/upgrade")
    public ResponseEntity<String> upgradeSubscription(@PathVariable Long userId) {
        subscriptionService.upgradeSubscription(userId);
        return ResponseEntity.ok("Subscription upgraded successfully");
    }

    @PostMapping("/{userId}/downgrade")
    public ResponseEntity<String> downgradeSubscription(@PathVariable Long userId) {
        subscriptionService.downgradeSubscription(userId);
        return ResponseEntity.ok("Subscription downgraded successfully");
    }
}
