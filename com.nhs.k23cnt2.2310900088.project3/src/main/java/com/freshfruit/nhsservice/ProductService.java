package com.freshfruit.nhsservice;

import com.freshfruit.nhsentity.Product;
import java.util.List;

public interface ProductService {
    List<Product> getAllProducts();
    Product getProductById(Integer id);
    Product saveProduct(Product product);
    void deleteProduct(Integer id);
    List<Product> getFeaturedProducts();

}
