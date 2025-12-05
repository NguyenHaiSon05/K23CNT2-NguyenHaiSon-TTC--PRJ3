package com.freshfruit.nhsrepository;

import com.freshfruit.nhsentity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
}
