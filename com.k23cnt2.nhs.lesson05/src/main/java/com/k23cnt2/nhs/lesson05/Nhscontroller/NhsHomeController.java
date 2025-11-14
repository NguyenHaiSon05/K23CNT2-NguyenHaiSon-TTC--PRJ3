package com.k23cnt2.nhs.lesson05.Nhscontroller;

import com.k23cnt2.nhs.lesson05.Nhsentity.NhsInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping
public class NhsHomeController {

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    @GetMapping("/about")
    public String about(Model model) {
        return "about";
    }

    @GetMapping("/contact")
    public String contact(Model model) {
        return "contact";
    }

    @GetMapping("/profile")
    public String profile(Model model) {

        List<NhsInfo> profile = new ArrayList<>();

        profile.add(new NhsInfo(
                "Nguyễn Hải Sơn",
                "Sinh viên CNTT",
                "2310900088",
                "2310900088@ntu.edu.vn",
                "https://ntu.edu.vn"
        ));

        model.addAttribute("DevmasterProfile", profile);

        return "profile";  // tên file profile.html
    }
}
