package mall.shopping.mall.service.subscription;

import lombok.NoArgsConstructor;
import mall.shopping.mall.entity.SubscriptionLevel;
import mall.shopping.mall.entity.User;
import mall.shopping.mall.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@NoArgsConstructor
public class SubscriptionService {


    @Autowired
    private UserRepository userRepository;




    /*
     * Upgrade API
     *
     * Level1 -> Level2
     *
     * Level 2 -> Level3
     *
     * */


    // 업그레이드 구독 서비스
    @Transactional
    public void upgradeSubscription(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(()
                -> new RuntimeException("User not found"));

        SubscriptionLevel userSubScriptionCheck = user.getSubscriptionLevel();

        if (userSubScriptionCheck == SubscriptionLevel.LEVEL_1) {
            user.setSubscriptionLevel(SubscriptionLevel.LEVEL_2);
        } else if (userSubScriptionCheck == SubscriptionLevel.LEVEL_2) {
            user.setSubscriptionLevel(SubscriptionLevel.LEVEL_3);
        }else{
            throw new IllegalArgumentException("No avail Check");
        }

        userRepository.save(user);

    }

    // 다운 그레이드 구독 서비스
    public void downgradeSubscription(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        switch (user.getSubscriptionLevel()) {
            case LEVEL_1 -> throw new IllegalStateException("Cannot downgrade from LEVEL_1");
            case LEVEL_2 -> user.setSubscriptionLevel(SubscriptionLevel.LEVEL_1);
            case LEVEL_3 -> user.setSubscriptionLevel(SubscriptionLevel.LEVEL_2);
            default -> throw new IllegalArgumentException("Invalid subscription level");
        }

        userRepository.save(user);
    }
}
