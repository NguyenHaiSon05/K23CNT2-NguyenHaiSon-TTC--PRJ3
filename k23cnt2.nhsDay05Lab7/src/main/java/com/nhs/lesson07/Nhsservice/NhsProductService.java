package com.nhs.lesson07.Nhsservice;

import com.nhs.lesson07.Nhsentity.NhsProduct;
import com.nhs.lesson07.Nhsrepository.NhsProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhsProductService {

    @Autowired
    private NhsProductRepository productRepository;

    public List<NhsProduct> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<NhsProduct> findById(Long id) {
        return productRepository.findById(id);
    }

    public void saveProduct(NhsProduct product) {
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }
}
