package mall.shopping.mall.controller;

import mall.shopping.mall.service.FundingService;
import mall.shopping.mall.util.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funding")
public class FundingController {


    private final FundingService fundingService;

    @Autowired
    public FundingController(FundingService fundingService) {
        this.fundingService = fundingService;
    }

    @PostMapping("/{projectId}/support")
    public ResponseEntity<String> supportFunding(@PathVariable("projectId") Long projectId,
                                                 @RequestParam("amount") Long amount,
                                                 @RequestParam("isSuccess") boolean isSuccess,
                                                 @AuthenticationPrincipal CustomUserDetails userDetails) {

        Long userId = userDetails.getUser().getId();
        System.out.println("지원 요청 - projectId: " + projectId + ", userId: " + userId);

        try {
            fundingService.processPayment(userId, projectId, amount, isSuccess);
            return ResponseEntity.ok("펀딩 후원 처리 완료");
        } catch (Exception e) {
            e.printStackTrace(); // 콘솔에 전체 스택 출력
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류: " + e.getMessage());
        }
    }




}
