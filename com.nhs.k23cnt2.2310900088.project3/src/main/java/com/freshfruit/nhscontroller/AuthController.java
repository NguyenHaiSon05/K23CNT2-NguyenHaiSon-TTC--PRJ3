package com.freshfruit.nhscontroller;

import com.freshfruit.nhsentity.OrderEntity;
import com.freshfruit.nhsentity.OrderStatus;
import com.freshfruit.nhsentity.UserEntity;
import com.freshfruit.nhsrepository.OrderRepository;
import com.freshfruit.nhsrepository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class AuthController {
    private final OrderRepository orderRepository;
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

    @GetMapping("/account/orders")
    public String myOrders(HttpSession session, Model model) {

        UserEntity loggedUser =
                (UserEntity) session.getAttribute("loggedUser");

        if (loggedUser == null) {
            return "redirect:/login";
        }

        List<OrderEntity> orders =
                orderRepository.findByUser_UserIdOrderByCreatedAtDesc(
                        loggedUser.getUserId()
                );

        model.addAttribute("user", loggedUser);
        model.addAttribute("orders", orders);
        model.addAttribute("section", "orders");

        return "auth/account";
    }

    @GetMapping("/account/orders/{id}")
    public String orderDetail(
            @PathVariable Integer id,
            HttpSession session,
            Model model) {

        UserEntity user =
                (UserEntity) session.getAttribute("loggedUser");

        if (user == null) {
            return "redirect:/login";
        }

        OrderEntity order =
                orderRepository.findById(id).orElse(null);

        // Không cho xem đơn của người khác
        if (order == null ||
                !order.getUser().getUserId().equals(user.getUserId())) {
            return "redirect:/account/orders";
        }

        model.addAttribute("user", user);
        model.addAttribute("order", order);
        model.addAttribute("items", order.getItems());
        model.addAttribute("section", "orders");

        return "auth/order-detail";
    }

    @PostMapping("/account/orders/cancel/{id}")
    public String cancelOrder(@PathVariable Integer id, HttpSession session) {

        UserEntity user = (UserEntity) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/login";

        OrderEntity order = orderRepository.findById(id).orElse(null);

        if (order != null
                && order.getUser().getUserId().equals(user.getUserId())
                && order.getStatus().equals("PENDING")) {

            order.setStatus(OrderStatus.CANCELLED);
            orderRepository.save(order);
        }

        return "redirect:/account/orders/" + id;
    }




}


