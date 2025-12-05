package com.freshfruit.nhsservice.impl;

import com.freshfruit.nhsentity.Product;
import com.freshfruit.nhsrepository.ProductRepository;
import com.freshfruit.nhsservice.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void deleteProduct(Integer id) {
        productRepository.deleteById(id);
    }
    @Override
    public List<Product> getFeaturedProducts() {
        return productRepository.findTop8ByOrderByCreatedAtDesc();
    }

}
