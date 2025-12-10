package com.freshfruit.nhsrepository;

import com.freshfruit.nhsentity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    List<Product> findTop8ByOrderByCreatedAtDesc();
}

