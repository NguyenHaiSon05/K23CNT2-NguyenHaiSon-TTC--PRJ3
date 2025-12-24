package com.freshfruit.nhscontroller.admin;

import com.freshfruit.nhsentity.Coupon;
import com.freshfruit.nhsentity.UserEntity;
import com.freshfruit.nhsrepository.CouponRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/coupons")
public class AdminCouponController {

    private final CouponRepository couponRepository;

    // =============================
    // CHECK ADMIN
    // =============================
    private boolean isAdmin(HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("loggedUser");
        return user != null
                && user.getRole() != null
                && "ADMIN".equals(user.getRole().getRoleName());
    }

    // =============================
    // LIST COUPON
    // =============================
    @GetMapping
    public String list(HttpSession session, Model model) {

        if (!isAdmin(session)) return "redirect:/login";

        model.addAttribute("coupons", couponRepository.findAll());
        return "admin/coupon/list";
    }

    // =============================
    // FORM ADD
    // =============================
    @GetMapping("/add")
    public String addForm(HttpSession session, Model model) {

        if (!isAdmin(session)) return "redirect:/login";

        model.addAttribute("coupon", new Coupon());
        return "admin/coupon/form";
    }

    // =============================
    // FORM EDIT
    // =============================
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id,
                       HttpSession session,
                       Model model) {

        if (!isAdmin(session)) return "redirect:/login";

        Optional<Coupon> couponOpt = couponRepository.findById(id);
        if (couponOpt.isEmpty()) return "redirect:/admin/coupons";

        model.addAttribute("coupon", couponOpt.get());
        return "admin/coupon/form";
    }

    // =============================
    // SAVE (ADD + UPDATE)
    // =============================
    @PostMapping("/save")
    public String save(@ModelAttribute Coupon coupon,
                       HttpSession session,
                       Model model) {

        if (!isAdmin(session)) return "redirect:/login";

        // Chuẩn hóa code
        coupon.setCode(coupon.getCode().trim().toUpperCase());

        // Validate ngày
        if (coupon.getStartDate() != null &&
                coupon.getEndDate() != null &&
                coupon.getEndDate().isBefore(coupon.getStartDate())) {

            model.addAttribute("error",
                    "Ngày kết thúc phải sau ngày bắt đầu");
            model.addAttribute("coupon", coupon);
            return "admin/coupon/form";
        }

        // Tránh null status
        if (coupon.getStatus() == null) {
            coupon.setStatus(false);
        }

        couponRepository.save(coupon);
        return "redirect:/admin/coupons";
    }

    // =============================
    // TOGGLE STATUS
    // =============================
    @PostMapping("/toggle/{id}")
    public String toggle(@PathVariable Integer id,
                         HttpSession session) {

        if (!isAdmin(session)) return "redirect:/login";

        couponRepository.findById(id).ifPresent(c -> {
            c.setStatus(!c.getStatus());
            couponRepository.save(c);
        });

        return "redirect:/admin/coupons";
    }
}
