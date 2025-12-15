package com.freshfruit.nhsconfig;

import com.freshfruit.nhsentity.UserEntity;
import com.freshfruit.nhsservice.CartService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalController {

    private final CartService cartService;

    @ModelAttribute("cartCount")
    public int cartCount(HttpSession session) {
        UserEntity user = (UserEntity) session.getAttribute("loggedUser");

        if (user == null) {
            return 0;
        }

        return cartService.getCartCount(user.getUserId());
    }
}
