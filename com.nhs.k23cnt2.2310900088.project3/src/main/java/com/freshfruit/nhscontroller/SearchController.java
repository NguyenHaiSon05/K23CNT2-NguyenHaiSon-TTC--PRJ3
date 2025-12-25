package com.freshfruit.nhscontroller;

import com.freshfruit.nhsentity.Product;
import com.freshfruit.nhsrepository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final ProductRepository productRepo;


    @GetMapping("/search")
    public String search(@RequestParam(value = "keyword", required = false) String keyword,
                         Model model) {

        List<Product> products = null;

        if (keyword != null && !keyword.trim().isEmpty()) {
            products = productRepo.findByNameContainingIgnoreCase(keyword.trim());
        }

        model.addAttribute("products", products);
        model.addAttribute("keyword", keyword);

        // 2. Trả về đúng đường dẫn file: "about/search"
        return "about/search";
    }
}