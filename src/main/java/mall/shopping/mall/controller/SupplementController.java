package mall.shopping.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/supplement")
public class SupplementController {


    @GetMapping
    public String getSupplement(){
        return "/supplement";
    }

    @GetMapping("calculator")
    public String getcalculator(){
        return "calculator";
    }
}
