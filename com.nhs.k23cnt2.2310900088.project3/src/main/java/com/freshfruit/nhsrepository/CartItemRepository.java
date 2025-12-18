package com.freshfruit.nhsrepository;

import com.freshfruit.nhsentity.Cart;
import com.freshfruit.nhsentity.CartItem;
import com.freshfruit.nhsentity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    CartItem findByCartAndProduct(Cart cart, Product product);
    @Modifying
    @Transactional
    void deleteById(Integer id);
}
