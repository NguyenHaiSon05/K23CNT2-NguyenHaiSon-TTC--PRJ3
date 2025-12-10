package com.freshfruit.nhsservice;

import com.freshfruit.nhsentity.Product;
import com.freshfruit.nhsrepository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepo;

    // Lấy 8 SP mới nhất
    public List<Product> getLatestProducts() {
        return productRepo.findTop8ByOrderByCreatedAtDesc();
    }

    // Lấy chi tiết SP
    public Product getProductById(Integer id) {
        return productRepo.findById(id).orElse(null);
    }
}
