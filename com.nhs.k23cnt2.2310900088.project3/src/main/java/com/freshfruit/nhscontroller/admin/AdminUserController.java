package com.freshfruit.nhscontroller.admin;

import com.freshfruit.nhsrepository.UserRepository;
import lombok.RequiredArgsConstructor;
import com.freshfruit.nhsentity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/users")
@RequiredArgsConstructor
public class AdminUserController {

    private final UserRepository userRepository;

    // 1. Danh sách user
    @GetMapping
    public String listUsers(Model model) {
        model.addAttribute("users", userRepository.findAll());
        return "admin/user/list";
    }

    // 2. Form sửa user
    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Integer id, Model model) {
        UserEntity user = userRepository.findById(id).orElse(null);
        model.addAttribute("user", user);
        return "admin/user/edit";
    }


    // 3. Lưu user
    @PostMapping("/save")
    public String saveUser(@ModelAttribute UserEntity userForm) {

        UserEntity user = userRepository.findById(userForm.getUserId()).orElse(null);
        if (user == null) {
            return "redirect:/admin/users";
        }

        user.setFullName(userForm.getFullName());
        user.setEmail(userForm.getEmail());
        user.setPhone(userForm.getPhone());
        user.setAddress(userForm.getAddress());
        user.setRole(userForm.getRole());
        user.setEnabled(userForm.isEnabled());


        userRepository.save(user);
        return "redirect:/admin/users";
    }


    // 4. Khóa / mở user
    @GetMapping("/toggle/{id}")
    public String toggleUser(@PathVariable Integer id) {
        UserEntity user = userRepository.findById(id).orElse(null);

        if (user != null && !"ADMIN".equals(user.getRole())) {
            user.setEnabled(!user.isEnabled());
            userRepository.save(user);
        }
        return "redirect:/admin/users";
    }
}


