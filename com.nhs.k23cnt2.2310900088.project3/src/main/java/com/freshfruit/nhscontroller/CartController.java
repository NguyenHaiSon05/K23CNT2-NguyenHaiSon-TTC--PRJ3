package com.freshfruit.nhscontroller;

import com.freshfruit.nhsentity.Coupon;
import com.freshfruit.nhsentity.UserEntity;
import com.freshfruit.nhsrepository.CouponRepository;
import com.freshfruit.nhsservice.CartService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    @Autowired
    private CouponRepository couponRepository;


    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {

        UserEntity user = (UserEntity) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/login";

        var cart = cartService.getCartByUser(user.getUserId());

        // 1. Tính tổng tiền GỐC (Chưa trừ gì cả)
        double originalTotalPrice = cart != null
                ? cartService.getTotalPrice(cart)
                : 0;

        // 2. Lấy thông tin giảm giá
        Coupon coupon = (Coupon) session.getAttribute("coupon");
        double discountPercent = 0;

        if (coupon != null) {
            discountPercent = coupon.getDiscountPercent();
        }

        // 3. Gửi dữ liệu sang HTML
        model.addAttribute("cartItems", cart != null ? cart.getItems() : null);

        // QUAN TRỌNG: Gửi giá gốc sang, không trừ tiền ở đây
        model.addAttribute("totalPrice", originalTotalPrice);

        model.addAttribute("discount", discountPercent);

        return "cart/cart";
    }


    @GetMapping("/cart/add/{id}")
    public String addToCart(@PathVariable Integer id,
                            HttpSession session) {

        UserEntity user = (UserEntity) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/login";

        cartService.addToCart(user.getUserId(), id);

        return "redirect:/product/" + id;
    }

    @GetMapping("/cart/remove/{itemId}")
    public String removeItem(@PathVariable Integer itemId,
                             HttpSession session) {

        UserEntity user = (UserEntity) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/login";

        cartService.removeItemByItemId(itemId);

        return "redirect:/cart";
    }


    @PostMapping("/cart/update")
    public String updateQuantity(@RequestParam Integer itemId,
                                 @RequestParam Integer quantity,
                                 HttpSession session) {

        UserEntity user = (UserEntity) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/login";

        cartService.updateQuantity(itemId, quantity);

        return "redirect:/cart";
    }





    @PostMapping("/apply-coupon")
    public String applyCoupon(@RequestParam String code, HttpSession session) {

        Optional<Coupon> couponOpt =
                couponRepository.findByCodeAndStatus(code, true);

        if (couponOpt.isPresent()) {
            session.setAttribute("coupon", couponOpt.get());
        } else {
            session.removeAttribute("coupon"); // mã sai
        }

        return "redirect:/cart";
    }


}
