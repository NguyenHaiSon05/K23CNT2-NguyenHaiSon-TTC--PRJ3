package com.freshfruit.nhscontroller;

import com.freshfruit.nhsentity.RoleEntity;
import com.freshfruit.nhsentity.UserEntity;
import com.freshfruit.nhsrepository.RoleRepository;
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
    private final RoleRepository roleRepository;
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

        // mã hóa mật khẩu
        user.setPassword(encoder.encode(user.getPassword()));

        // gán ROLE_USER
        RoleEntity role = roleRepository.findByRoleName("ROLE_USER");
        user.setRole(role);

        userRepository.save(user);

        return "redirect:/login?success";
    }
}
