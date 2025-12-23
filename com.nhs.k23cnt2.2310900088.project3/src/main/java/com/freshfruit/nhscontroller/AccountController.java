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
@RequestMapping("/account")
public class AccountController {

    private final OrderRepository orderRepository;
    private final UserRepository userRepo;

    // ACCOUNT HOME
    @GetMapping
    public String accountHome(HttpSession session, Model model) {
        UserEntity user = (UserEntity) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/login";

        List<OrderEntity> orders =
                orderRepository.findByUser_UserIdOrderByCreatedAtDesc(
                        user.getUserId());

        model.addAttribute("user", user);
        model.addAttribute("orders", orders);
        model.addAttribute("section", "orders");

        return "auth/account";
    }

    // ORDERS
    @GetMapping("/orders")
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

    @GetMapping("/orders/{id}")
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

    @PostMapping("/orders/cancel/{id}")
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

    // PROFILE
    @GetMapping("/profile")
    public String profile(HttpSession session, Model model) {
        UserEntity user = (UserEntity) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/login";

        model.addAttribute("user", user);
        model.addAttribute("section", "profile");

        return "auth/account";
    }

    // =====================
    // UPDATE PROFILE
    // =====================
    @PostMapping("/profile")
    public String updateProfile(
            @ModelAttribute("user") UserEntity formUser,
            HttpSession session) {

        UserEntity user =
                (UserEntity) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/login";

        user.setFullName(formUser.getFullName());
        user.setPhone(formUser.getPhone());
        user.setAddress(formUser.getAddress());

        userRepo.save(user);
        session.setAttribute("loggedUser", user);

        return "redirect:/account/profile";
    }

    // ADDRESS
    @GetMapping("/address")
    public String address(HttpSession session, Model model) {

        UserEntity user = (UserEntity) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/login";

        model.addAttribute("user", user);
        model.addAttribute("section", "address");

        return "auth/account";
    }


}

