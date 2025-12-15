package com.freshfruit.nhscontroller;

import com.freshfruit.nhsentity.UserEntity;
import com.freshfruit.nhsrepository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class AuthController {

    private final UserRepository userRepo;

    // ==========================
    // GET: LOGIN PAGE
    // ==========================
    @GetMapping("/login")
    public String loginPage(Model model) {
        model.addAttribute("pageTitle", "Đăng Nhập");

        // Object rỗng cho form Binding
        model.addAttribute("user", new UserEntity());

        return "auth/login";
    }

    // ==========================
    // POST: LOGIN
    // ==========================
    @PostMapping("/login")
    public String login(
            @ModelAttribute("user") UserEntity formUser,
            HttpSession session,
            Model model
    ) {

        // 1️⃣ Kiểm tra trống trước
        if (formUser.getEmail() == null || formUser.getEmail().isBlank() ||
                formUser.getPassword() == null || formUser.getPassword().isBlank()) {

            model.addAttribute("error", "Vui lòng nhập email và mật khẩu!");
            return "auth/login";
        }

        // 2️⃣ Tìm user trong DB
        UserEntity dbUser = userRepo.findByEmail(formUser.getEmail());

        // 3️⃣ Nếu không tồn tại hoặc sai mật khẩu
        if (dbUser == null || !dbUser.getPassword().equals(formUser.getPassword())) {
            model.addAttribute("error", "Sai email hoặc mật khẩu!");
            return "auth/login";
        }

        // 4️⃣ Login thành công
        session.setAttribute("loggedUser", dbUser);

        // ✅ PHÂN QUYỀN SAU KHI LOGIN
        if (dbUser.getRole() != null &&
                "ADMIN".equals(dbUser.getRole().getRoleName())) {

            return "redirect:/admin";
        }

        // USER thường
        return "redirect:/";


    }


    // ==========================
    // GET: REGISTER PAGE
    // ==========================
    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("pageTitle", "Đăng Ký");
        model.addAttribute("user", new UserEntity());
        return "auth/register";
    }

    // ==========================
    // POST: REGISTER
    // ==========================
    @PostMapping("/register")
    public String register(
            @ModelAttribute("user") UserEntity formUser,
            Model model
    ) {

        // 1️⃣ Kiểm tra form trống
        if (formUser.getFullName() == null || formUser.getFullName().isBlank() ||
                formUser.getEmail() == null || formUser.getEmail().isBlank() ||
                formUser.getPassword() == null || formUser.getPassword().isBlank()) {

            model.addAttribute("error", "Vui lòng nhập đầy đủ họ tên, email và mật khẩu!");
            return "auth/register";
        }

        // 2️⃣ Kiểm tra email trùng
        if (userRepo.findByEmail(formUser.getEmail()) != null) {
            model.addAttribute("error", "Email đã tồn tại!");
            return "auth/register";
        }

        // 3️⃣ Lưu user mới
        UserEntity newUser = new UserEntity();
        newUser.setFullName(formUser.getFullName());
        newUser.setEmail(formUser.getEmail());
        newUser.setPassword(formUser.getPassword());

        userRepo.save(newUser);

        return "redirect:/login";
    }


    // ==========================
    // LOGOUT
    // ==========================
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/account")
    public String accountPage(HttpSession session, Model model) {

        UserEntity loggedUser = (UserEntity) session.getAttribute("loggedUser");

        if (loggedUser == null) {
            return "redirect:/login";
        }

        model.addAttribute("user", loggedUser);
        return "auth/account";
    }




}


