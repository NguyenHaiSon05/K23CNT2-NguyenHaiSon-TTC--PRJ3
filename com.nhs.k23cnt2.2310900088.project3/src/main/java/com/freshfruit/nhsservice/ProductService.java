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
    // Lấy 8 sp tự chọn
    public List<Product> getBestSellerProducts() {
        return productRepo.findByProductIdIn(List.of(51, 52, 53, 54, 55, 56, 57, 58));
    }

    // Lấy chi tiết SP
    public Product getProductById(Integer id) {
        return productRepo.findById(id).orElse(null);
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }


    public List<Product> getProductsByNeed(String need) {
        return switch (need) {

            // THEO NHU CẦU
            case "chuc-mung" -> productRepo.findByProductIdIn(List.of(51, 52, 53 ,63));
            case "chia-buon" -> productRepo.findByProductIdIn(List.of(54, 55, 69, 67));
            case "cha-me" -> productRepo.findByProductIdIn(List.of(52, 53, 55, 68));
            case "nguoi-yeu" -> productRepo.findByProductIdIn(List.of(62, 61, 51, 52));

            // THEO NGÂN SÁCH
            case "duoi-1tr" -> productRepo.findByProductIdIn(List.of(59, 60)); // Ví dụ
            case "1-2tr" -> productRepo.findByProductIdIn(List.of(51, 53));
            case "2-3tr" -> productRepo.findByProductIdIn(List.of(52, 54));
            case "tren-3tr" -> productRepo.findByProductIdIn(List.of(55, 61));

            // LOẠI HÌNH
            case "hoa-tuoi" -> productRepo.findByProductIdIn(List.of(51, 52));
            case "ruou" -> productRepo.findByProductIdIn(List.of(55));

            // QUÀ LỄ TẾT
            case "8-3" -> productRepo.findByProductIdIn(List.of(51, 52, 53));
            case "tet" -> productRepo.findByProductIdIn(List.of(53, 54, 55));
            case "20-11" -> productRepo.findByProductIdIn(List.of(52, 56));

            default -> productRepo.findAll();
        };
    }

    public List<Product> getProductsByCategory(String category) {
        return switch (category) {
            case "gift"     -> productRepo.findByCategory_CategoryId(3);
            case "domestic" -> productRepo.findByCategory_CategoryId(1);
            case "import"   -> productRepo.findByCategory_CategoryId(2);
            case "dry"      -> productRepo.findByCategory_CategoryId(4);
            default -> productRepo.findAll();
        };
    }

    // ===== ADMIN CRUD =====

    // Lấy theo ID (admin dùng)
    public Product getById(Integer id) {
        return productRepo.findById(id).orElse(null);
    }

    // Lưu (tự phân biệt thêm / sửa)
    public void save(Product product) {
        productRepo.save(product);
    }

    // Xoá
    public void delete(Integer id) {
        productRepo.deleteById(id);
    }





}
