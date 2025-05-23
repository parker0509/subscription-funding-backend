package mall.shopping.mall.controller;

import mall.shopping.mall.dto.LoginRequest;
import mall.shopping.mall.dto.RegisterRequest;
import mall.shopping.mall.service.login.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest){
        try {
            String token = loginService.loginUserService(loginRequest.getEmail(), loginRequest.getPassword());

            // ✅ JSON 형식으로 응답
            System.out.println("token = " + token);

            return ResponseEntity.ok(Map.of("token", token));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("error", e.getMessage()));
        }
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest) {
        System.out.println("회원가입 요청: " + registerRequest.toString());

        // 여기서 DB 저장 로직 추가 필요 (JPA 또는 Service 활용)
        return ResponseEntity.ok("회원가입 성공!");
    }



}
