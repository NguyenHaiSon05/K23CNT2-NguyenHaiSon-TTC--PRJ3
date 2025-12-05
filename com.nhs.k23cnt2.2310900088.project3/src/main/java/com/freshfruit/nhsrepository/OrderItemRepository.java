package com.freshfruit.nhsrepository;

import com.freshfruit.nhsentity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
}
