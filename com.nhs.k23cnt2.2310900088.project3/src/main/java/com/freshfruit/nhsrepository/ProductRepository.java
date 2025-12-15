package com.freshfruit.nhsrepository;

import com.freshfruit.nhsentity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    // Lấy 8 sp mới nhất (dựa theo createdAt hoặc id)
    List<Product> findTop8ByOrderByCreatedAtDesc();

    // Nếu không có createdAt thì dùng id (id mới nhất trước)
     List<Product> findTop8ByOrderByProductIdDesc();

    // Lấy 8 sp bán chạy nhất (dựa theo trường sold)
    List<Product> findTop8ByOrderBySoldDesc();

    List<Product> findByProductIdIn(List<Integer> ids);

    List<Product> findByCategory_CategoryId(Integer categoryId);

}

