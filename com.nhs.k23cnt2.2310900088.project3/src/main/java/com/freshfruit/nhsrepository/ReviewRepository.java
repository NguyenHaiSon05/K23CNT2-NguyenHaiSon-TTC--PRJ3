package com.freshfruit.nhsrepository;

import com.freshfruit.nhsentity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Integer> {
    List<Review> findByProductProductId(Integer productId);
}
