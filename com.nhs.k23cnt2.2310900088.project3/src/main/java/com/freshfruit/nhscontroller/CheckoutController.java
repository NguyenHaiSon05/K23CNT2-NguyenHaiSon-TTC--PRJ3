package com.freshfruit.nhscontroller;

import com.freshfruit.nhsentity.*;
import com.freshfruit.nhsrepository.OrderItemRepository;
import com.freshfruit.nhsrepository.OrderRepository;
import com.freshfruit.nhsservice.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
public class CheckoutController {

    private final CartService cartService;
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;

    @GetMapping("/checkout")
    public String checkoutPage(HttpSession session, Model model) {

        UserEntity user = (UserEntity) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/login";

        Cart cart = cartService.getCartByUser(user.getUserId());
        if (cart == null || cart.getItems().isEmpty()) {
            return "redirect:/cart";
        }

        double total = cartService.getTotalPrice(cart);

        Coupon coupon = (Coupon) session.getAttribute("coupon");
        double discount = 0;
        double finalTotal = total;

        if (coupon != null) {
            discount = coupon.getDiscountPercent();
            finalTotal = total * (100 - discount) / 100;
        }

        model.addAttribute("cart", cart);
        model.addAttribute("total", total);
        model.addAttribute("discount", discount);
        model.addAttribute("finalTotal", finalTotal);

        return "checkout/checkout";
    }

    // ================= ĐẶT HÀNG =================
    @PostMapping("/checkout/place-order")
    public String placeOrder(
            @RequestParam String fullName,
            @RequestParam String phone,
            @RequestParam String address,
            @RequestParam(required = false) String note,
            @RequestParam String paymentMethod,
            HttpSession session
    ) {
        UserEntity user = (UserEntity) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/login";

        Cart cart = cartService.getCartByUser(user.getUserId());
        if (cart == null || cart.getItems().isEmpty()) {
            return "redirect:/cart";
        }

        // ===== 1. TÍNH TIỀN =====
        double total = cartService.getTotalPrice(cart);

        Coupon coupon = (Coupon) session.getAttribute("coupon");
        double finalTotal = total;

        if (coupon != null) {
            finalTotal = total * (100 - coupon.getDiscountPercent()) / 100;
        }

        // ===== 2. TẠO ORDER =====
        OrderEntity order = new OrderEntity();
        order.setUser(user);
        order.setFullName(fullName);
        order.setPhone(phone);
        order.setAddress(address);
        order.setNote(note);
        order.setPaymentMethod(paymentMethod);
        order.setTotalAmount(finalTotal);
        order.setCreatedAt(LocalDateTime.now());

        // 🔥 PHÂN BIỆT COD / BANK
        if ("COD".equals(paymentMethod)) {
            order.setStatus(OrderStatus.PENDING);
        } else if ("QR".equals(paymentMethod)) {
            order.setStatus(OrderStatus.WAIT_PAYMENT);
        }



        orderRepository.save(order);


        // ===== 3. ORDER ITEMS =====
        for (CartItem cartItem : cart.getItems()) {
            OrderItem item = new OrderItem();
            item.setOrder(order);
            item.setProduct(cartItem.getProduct());
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(cartItem.getProduct().getPrice());

            orderItemRepository.save(item);
        }

        // ===== 4. XÓA GIỎ =====
        cartService.clearCart(user.getUserId());

        // ===== 5. XÓA COUPON =====
        session.removeAttribute("coupon");

        return "redirect:/checkout/success";
    }

    @GetMapping("/checkout/success")
    public String success() {
        return "checkout/success";
    }
}

