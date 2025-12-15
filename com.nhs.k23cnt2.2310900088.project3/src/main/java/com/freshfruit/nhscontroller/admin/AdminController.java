package com.freshfruit.nhscontroller.admin;

import com.freshfruit.nhsentity.UserEntity;
import com.freshfruit.nhsrepository.ProductRepository;
import com.freshfruit.nhsrepository.CategoryRepository;
import com.freshfruit.nhsrepository.OrderRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    private final ProductRepository productRepo;
    private final CategoryRepository categoryRepo;
    private final OrderRepository orderRepo;

    public AdminController(ProductRepository productRepo,
                           CategoryRepository categoryRepo,
                           OrderRepository orderRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
        this.orderRepo = orderRepo;
    }

    @GetMapping("/admin")
    public String adminDashboard(HttpSession session, Model model) {

        UserEntity user = (UserEntity) session.getAttribute("loggedUser");

        if (user == null) return "redirect:/login";

        if (user.getRole() == null ||
                !"ADMIN".equals(user.getRole().getRoleName())) {
            return "access-denied";
        }

        // ====== SỐ LIỆU THỰC TẾ ======
        model.addAttribute("adminName", user.getFullName());
        model.addAttribute("totalProducts", productRepo.count());
        model.addAttribute("totalCategories", categoryRepo.count());
        model.addAttribute("totalOrders", orderRepo.count());
        model.addAttribute("totalRevenue", orderRepo.getTotalRevenue());

        return "admin/dashboard";
    }
}
