package mall.shopping.mall.controller;

import mall.shopping.mall.service.FundingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funding")
public class FundingController {


    private final FundingService fundingService;

    @Autowired
    public FundingController(FundingService fundingService) {
        this.fundingService = fundingService;
    }

    @PostMapping("/{fundingId}/support")
    public ResponseEntity<String> supportFunding(@RequestParam Long userId,
                                                 @PathVariable Long fundingId,
                                                 @RequestParam Long amount,
                                                 @RequestParam boolean isSuccess) {
        fundingService.processPayment(userId, fundingId, amount, isSuccess);
        return ResponseEntity.ok("펀딩 후원 처리 완료");
    }




}
