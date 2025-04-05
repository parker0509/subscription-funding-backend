package mall.shopping.mall.controller;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import mall.shopping.mall.dto.RegisterRequest;
import mall.shopping.mall.entity.User;
import mall.shopping.mall.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Tag(name = "register API", description = "회원가입 API")
@RequestMapping("/api/join")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterRequest registerRequest) {
        System.out.println("회원가입 요청: " + registerRequest.toString());

        userService.createUser(registerRequest);
        // 여기서 DB 저장 로직 추가 필요 (JPA 또는 Service 활용)
        return ResponseEntity.ok("회원가입 성공!");
    }



    // 회원가입 폼 이동
    @GetMapping
    public String joinForm(Model model){

        model.addAttribute("user",new User());

        return "join-form";
    }



}
