package com.freshfruit.nhsrepository;

import com.freshfruit.nhsentity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    // Tổng doanh thu (chỉ tính đơn hoàn thành)
    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) " +
            "FROM OrderEntity o WHERE o.status = 'COMPLETED'")
    Long getTotalRevenue();
}
