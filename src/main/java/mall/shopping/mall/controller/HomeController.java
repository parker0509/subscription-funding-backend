package mall.shopping.mall.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import mall.shopping.mall.dto.ProjectResponseDto;
import mall.shopping.mall.entity.Project;
import mall.shopping.mall.entity.User;
import mall.shopping.mall.service.project.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Home", description = "쇼핑몰 홈 페이지 관련 API")
@RequestMapping("/api")
@RestController
public class HomeController {

    @Autowired
    private ProjectService projectService;
    private HttpSession httpSession;

    // 홈 페이지 (쇼핑몰 소개 및 상품 목록)
    @Operation(summary = "홈 페이지", description = "쇼핑몰의 홈 페이지로, 최신 상품 목록을 보여줍니다.")
    @ApiResponse(responseCode = "200", description = "홈 페이지 로드 성공", content = @Content(mediaType = "text/html", examples = {@ExampleObject("홈 페이지가 정상적으로 로드되었습니다.")}))
    @GetMapping("/")
    public ResponseEntity<String> home(Model model, User user) {
        // 세션에 사용자 이름 저장

        if (user != null) {
            String username = user.getName();
            model.addAttribute("username", username);
        }

        return ResponseEntity.status(200).body("홈페이지 로드 렌더링 // 수정중");  // home.html 파일로 리턴 (이 파일은 홈 페이지를 렌더링합니다.)
    }

    // Swagger에서 API 설명을 위한 헬퍼 메소드
    @Operation(summary = "홈 페이지 확인", description = "홈 페이지가 정상적으로 동작하는지 확인하는 API")
    @ApiResponse(responseCode = "200", description = "OK", content = @Content(mediaType = "text/plain", examples = {@ExampleObject("홈 페이지가 정상적으로 동작합니다.")}))
    @GetMapping("/home")
    public String checkHomeApi() {
        return "OK";  // 홈 페이지가 정상적으로 동작하는지 확인하는 API
    }

    @GetMapping("/payment")
    public ResponseEntity<String> showSubscriptionPage(Model model) {
        List<ProjectResponseDto> projects = projectService.getAllProjects(); // 예제 서비스 호출
        model.addAttribute("projects", projects);
        return ResponseEntity.ok("ok"); // HTML 파일 이름
    }
}
