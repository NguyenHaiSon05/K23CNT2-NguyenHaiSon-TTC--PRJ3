package com.freshfruit.nhscontroller;

import com.freshfruit.nhsentity.Product;
import com.freshfruit.nhsservice.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @GetMapping("/")
    public String home(Model model) {

        List<Product> latestProducts = productService.getLatestProducts();

        model.addAttribute("latestProducts", productService.getLatestProducts());
        model.addAttribute("bestSellerProducts", productService.getBestSellerProducts());


        return "home/index";
    }

}
