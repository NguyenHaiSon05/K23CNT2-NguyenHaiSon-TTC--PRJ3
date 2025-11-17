package k23cnt2.nhs.day02;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {

    @GetMapping("/hello")
    @ResponseBody   // >>> BẮT BUỘC PHẢI THÊM
    public String hello() {
        return "Xin chào từ Spring Boot!";
    }
}
