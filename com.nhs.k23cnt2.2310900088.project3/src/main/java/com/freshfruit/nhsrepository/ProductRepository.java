package com.freshfruit.nhsrepository;

import com.freshfruit.nhsentity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    List<Product> findTop8ByOrderByCreatedAtDesc();

}
