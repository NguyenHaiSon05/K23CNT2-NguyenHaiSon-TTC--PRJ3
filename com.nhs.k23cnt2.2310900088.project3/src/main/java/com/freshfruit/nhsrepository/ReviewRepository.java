package com.freshfruit.nhsrepository;

import com.freshfruit.nhsentity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Integer> {

    List<Review> findByProduct_ProductIdOrderByCreatedAtDesc(Integer productId);

    boolean existsByUser_UserIdAndProduct_ProductId(Integer userId, Integer productId);

    @Query("SELECT AVG(r.rating) FROM Review r WHERE r.product.productId = :pid")
    Double avgRating(@Param("pid") Integer productId);
}

