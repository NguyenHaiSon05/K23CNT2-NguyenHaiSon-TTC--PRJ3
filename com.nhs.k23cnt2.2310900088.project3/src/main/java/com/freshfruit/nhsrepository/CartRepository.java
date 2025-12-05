package com.freshfruit.nhsrepository;

import com.freshfruit.nhsentity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    Cart findByUserUserId(Integer userId);
}
