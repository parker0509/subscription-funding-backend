package mall.shopping.mall.service;

import mall.shopping.mall.repository.FundingRepository;
import mall.shopping.mall.repository.FundingSupportRepository;
import mall.shopping.mall.repository.UserRepository;
import mall.shopping.mall.entity.Funding;
import mall.shopping.mall.entity.FundingSupport;
import mall.shopping.mall.entity.PaymentStatus;
import mall.shopping.mall.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FundingService {

    @Autowired
    private final FundingRepository fundingRepository;
    private final FundingSupportRepository fundingSupportRepository;
    private final UserRepository userRepository;


    @Transactional
    public void processPayment(Long userId, Long fundingId, Long amount, boolean isSuccess) {

        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("유저를 찾을 수 없습니다."));
        Funding funding = fundingRepository.findById(fundingId).orElseThrow(() -> new RuntimeException("찾을 수 없는 펀딩입니다."));

        FundingSupport fundingSupport = new FundingSupport();
        fundingSupport.setId(fundingId);
        fundingSupport.setUser(user);
        fundingSupport.setAmount(amount);
        fundingSupport.setPaymentStatus(isSuccess ? PaymentStatus.SUCCESS : PaymentStatus.FAILED);


        fundingSupportRepository.save(fundingSupport);


        if (isSuccess) {

            funding.setCurrentAmount(funding.getCurrentAmount() + amount);
            fundingRepository.save(funding);


        }


    }


}
