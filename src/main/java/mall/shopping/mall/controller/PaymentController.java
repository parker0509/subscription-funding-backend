package mall.shopping.mall.controller;

import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import mall.shopping.mall.service.IamportService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {


    private final IamportService iamportService;

    @Autowired
    public PaymentController(IamportService iamportService) {
        this.iamportService = iamportService;
    }

    /**
     * 결제 검증 API
     */
    @GetMapping("/validate/{impUid}")
    public IamportResponse<Payment> validatePayment(@PathVariable String impUid) throws IOException, IamportResponseException {
        return iamportService.validatePayment(impUid);
    }

    /**
     * 결제 취소 API
     */
    @PostMapping("/cancel/{impUid}")
    public IamportResponse<Payment> cancelPayment(@PathVariable CancelData impUid) throws IOException, IamportResponseException {
        return iamportService.cancelPayment(impUid);
    }
}
