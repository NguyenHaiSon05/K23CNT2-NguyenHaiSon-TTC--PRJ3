package com.freshfruit.nhsrepository;

import com.freshfruit.nhsentity.Cart;
import com.freshfruit.nhsentity.CartItem;
import com.freshfruit.nhsentity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    CartItem findByCartAndProduct(Cart cart, Product product);
}
