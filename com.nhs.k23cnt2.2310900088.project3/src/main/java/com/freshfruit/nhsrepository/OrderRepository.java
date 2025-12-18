package com.freshfruit.nhsrepository;

import com.freshfruit.nhsentity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {

    List<OrderEntity> findByUser_UserIdOrderByCreatedAtDesc(Integer userId);
    // Tổng doanh thu (chỉ tính đơn hoàn thành)
    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) " +
            "FROM OrderEntity o WHERE o.status = 'COMPLETED'")
    Long getTotalRevenue();
}
