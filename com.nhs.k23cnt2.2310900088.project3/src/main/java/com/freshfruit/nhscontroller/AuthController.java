package com.freshfruit.nhscontroller;

import com.freshfruit.nhsentity.UserEntity;
import com.freshfruit.nhsrepository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @GetMapping("/login")
    public String loginPage() {
        return "auth/login";
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new UserEntity());
        return "auth/register";
    }

    @PostMapping("/register")
    public String registerSubmit(@ModelAttribute UserEntity user) {

        user.setPassword(encoder.encode(user.getPassword()));
        user.setRole("ROLE_USER"); // mặc định user thường

        userRepository.save(user);

        return "redirect:/login?success";
    }
}
