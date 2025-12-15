package com.freshfruit.nhscontroller;

import com.freshfruit.nhsservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ProductListController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public String listProducts(
            @RequestParam(required = false) String need,
            @RequestParam(required = false) String category,
            Model model) {

        if (need != null) {
            model.addAttribute("products", productService.getProductsByNeed(need));
        }
        else if (category != null) {
            model.addAttribute("products", productService.getProductsByCategory(category));
        }
        else {
            model.addAttribute("products", productService.getAllProducts());
        }

        return "product/list";
    }
}
