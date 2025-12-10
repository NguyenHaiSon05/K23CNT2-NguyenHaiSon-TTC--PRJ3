package com.freshfruit.nhscontroller;

import com.freshfruit.nhsentity.Product;
import com.freshfruit.nhsservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/{id}")
    public String detail(@PathVariable Integer id, Model model) {

        Product product = productService.getProductById(id);

        if (product == null) {
            return "redirect:/";
        }

        model.addAttribute("product", product);
        return "product/detail";
    }
}
