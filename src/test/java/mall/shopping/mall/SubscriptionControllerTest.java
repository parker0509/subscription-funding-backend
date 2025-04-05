package mall.shopping.mall;


import mall.shopping.mall.service.subscription.SubscriptionService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;



@ExtendWith(MockitoExtension.class)
@WebMvcTest(SubscriptionControllerTest.class)
@WithMockUser(username = "testUser", roles = {"USER"}) // 가짜 로그인 유저 추가
public class SubscriptionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SubscriptionService subscriptionService;


    @Test
    void testUpgradeSubscription() throws Exception {

        Long userId = 1L;

        doNothing().when(subscriptionService).upgradeSubscription(userId);

        mockMvc.perform(post("/api/subscription/" + userId + "/upgrade")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().string("Subscription upgraded successfully"));

        verify(subscriptionService, times(1)).upgradeSubscription(userId);
    }


    @Test
    void testDowngradeSubscription() throws Exception {
        Long userId = 1L;

        // Mock behavior 설정
        doNothing().when(subscriptionService).downgradeSubscription(userId);

        // POST 요청 수행
        mockMvc.perform(post("/api/subscription/" + userId + "/downgrade")
                        .with(csrf())) // CSRF 토큰 포함
                .andExpect(status().isOk()) // 200 OK 상태 기대
                .andExpect(content().string("Subscription downgraded successfully"));

        // 서비스 메소드 호출 확인
        verify(subscriptionService, times(1)).downgradeSubscription(userId);
    }




}




