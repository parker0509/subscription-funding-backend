package mall.shopping.mall.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/web")
@RestController
public class MainController {


    @GetMapping("/main")
    public ResponseEntity<String> mainController() {
        return ResponseEntity.status(200).body("메인 페이지 성공");
    }
}
