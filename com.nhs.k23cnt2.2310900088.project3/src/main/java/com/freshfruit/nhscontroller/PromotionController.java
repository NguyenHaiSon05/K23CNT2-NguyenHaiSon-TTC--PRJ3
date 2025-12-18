package com.freshfruit.nhscontroller;

import com.freshfruit.nhsrepository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PromotionController {

    @Autowired
    private CouponRepository couponRepository;

    @GetMapping("/promotion")
    public String promotionPage(Model model) {

        // chỉ lấy coupon đang bật
        model.addAttribute("coupons",
                couponRepository.findByStatus(true));

        return "about/promotion";
    }
}

