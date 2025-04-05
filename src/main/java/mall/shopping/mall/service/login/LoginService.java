package mall.shopping.mall.service.login;


import jakarta.servlet.http.HttpSession;
import mall.shopping.mall.entity.User;
import mall.shopping.mall.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public LoginService(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // !!로그인에 필요한 기능
    // 로그인 검증 (email 로 검증)

    /*
     * 로그인에 필요한 기능
     * 1. 로그인시 UserRepository와 검증
     * 2. Session 과 JWT 구분
     * 3. 추후 생각 날시 적기
     *
     * */


    // >> login 검증 기능
    public String loginUserService(String email, String password) {

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        return "JWT_TOKEN";

    }

}
