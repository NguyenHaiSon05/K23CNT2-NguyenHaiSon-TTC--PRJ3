package com.freshfruit.nhsrepository;

import com.freshfruit.nhsentity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    Optional<Coupon> findByCodeAndStatus(String code, Boolean status);

    // ✅ DÙNG CHO TRANG KHUYẾN MÃI
    List<Coupon> findByStatus(Boolean status);
}

