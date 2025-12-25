package com.freshfruit.nhscontroller;

import com.freshfruit.nhsentity.Product;
import com.freshfruit.nhsrepository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController // Quan trọng: Dùng RestController để trả về JSON
@RequiredArgsConstructor
public class SearchApiController {

    private final ProductRepository productRepo;

    @GetMapping("/api/search-suggest")
    public ResponseEntity<List<Map<String, Object>>> searchSuggest(@RequestParam String keyword) {
        List<Map<String, Object>> results = new ArrayList<>();

        if (keyword == null || keyword.trim().isEmpty()) {
            return ResponseEntity.ok(results);
        }

        // Tìm sản phẩm theo tên
        List<Product> products = productRepo.findByNameContainingIgnoreCase(keyword.trim());

        // Chỉ lấy 5 sản phẩm đầu tiên để gợi ý cho nhanh
        int limit = Math.min(products.size(), 5);

        for (int i = 0; i < limit; i++) {
            Product p = products.get(i);
            // Tạo một map chứa thông tin cần thiết
            Map<String, Object> map = new HashMap<>();
            map.put("id", p.getProductId());
            map.put("name", p.getName());
            map.put("price", p.getPrice());
            map.put("image", p.getImage());
            map.put("unit", p.getUnit()); // ví dụ: kg, hộp
            results.add(map);
        }

        return ResponseEntity.ok(results);
    }
}