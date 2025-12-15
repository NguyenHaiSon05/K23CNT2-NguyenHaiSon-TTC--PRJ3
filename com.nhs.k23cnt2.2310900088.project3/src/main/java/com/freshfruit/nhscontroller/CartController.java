package com.freshfruit.nhscontroller;

import com.freshfruit.nhsentity.UserEntity;
import com.freshfruit.nhsservice.CartService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;

    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {

        UserEntity user = (UserEntity) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/login";

        var cart = cartService.getCartByUser(user.getUserId());

        model.addAttribute("cartItems",
                cart != null ? cart.getItems() : null);

        model.addAttribute("totalPrice",
                cart != null ? cartService.getTotalPrice(cart) : 0);

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

    @GetMapping("/cart/remove/{id}")
    public String removeItem(@PathVariable Integer id,
                             HttpSession session) {

        UserEntity user = (UserEntity) session.getAttribute("loggedUser");
        if (user == null) return "redirect:/login";

        cartService.removeItem(user.getUserId(), id);

        return "redirect:/cart";
    }
}
