package mall.shopping.mall;

import mall.shopping.mall.entity.Funding;
import mall.shopping.mall.entity.FundingSupport;
import mall.shopping.mall.entity.User;
import mall.shopping.mall.repository.FundingRepository;
import mall.shopping.mall.repository.FundingSupportRepository;
import mall.shopping.mall.repository.UserRepository;
import mall.shopping.mall.service.FundingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private FundingRepository fundingRepository;

    @Mock
    private FundingSupportRepository fundingSupportRepository;

    @InjectMocks
    private FundingService paymentService;

    private User testUser;
    private Funding testFunding;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        testUser = new User();
        testUser.setId(1L);

        testFunding = new Funding();
        testFunding.setId(1L);
        testFunding.setCurrentAmount(5000L);
    }

    @Test
    void testProcessPayment_Success() {
        // Given
        Long amount = 1000L;
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(testUser));
        when(fundingRepository.findById(1L)).thenReturn(java.util.Optional.of(testFunding));

        System.out.println("=== [TEST START] testProcessPayment_Success ===");
        System.out.println("초기 펀딩 금액: " + testFunding.getCurrentAmount());

        // When
        paymentService.processPayment(1L, 1L, amount, true);

        // Then
        System.out.println("결제 성공 후 펀딩 금액: " + testFunding.getCurrentAmount());
        assertEquals(6000L, testFunding.getCurrentAmount());

        verify(fundingSupportRepository, times(1)).save(any(FundingSupport.class));
        verify(fundingRepository, times(1)).save(testFunding);

        System.out.println("=== [TEST END] testProcessPayment_Success ===\n");
    }

    @Test
    void testProcessPayment_Failed() {
        // Given
        Long amount = 1000L;
        when(userRepository.findById(1L)).thenReturn(java.util.Optional.of(testUser));
        when(fundingRepository.findById(1L)).thenReturn(java.util.Optional.of(testFunding));

        System.out.println("=== [TEST START] testProcessPayment_Failed ===");
        System.out.println("초기 펀딩 금액: " + testFunding.getCurrentAmount());

        // When
        paymentService.processPayment(1L, 1L, amount, false);

        // Then
        System.out.println("결제 실패 후 펀딩 금액 (변동 없음): " + testFunding.getCurrentAmount());
        assertEquals(5000L, testFunding.getCurrentAmount());

        verify(fundingSupportRepository, times(1)).save(any(FundingSupport.class));
        verify(fundingRepository, never()).save(testFunding);

        System.out.println("=== [TEST END] testProcessPayment_Failed ===\n");
    }


}
