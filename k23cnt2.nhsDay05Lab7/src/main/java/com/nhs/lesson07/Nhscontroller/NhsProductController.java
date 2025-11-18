package com.nhs.lesson07.Nhscontroller;

import com.nhs.lesson07.Nhsentity.NhsProduct;
import com.nhs.lesson07.Nhsservice.NhsProductService;
import com.nhs.lesson07.Nhsservice.NhsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nhs/products")
public class NhsProductController {

    @Autowired
    private NhsProductService productService;

    @Autowired
    private NhsCategoryService categoryService;

    // Danh sách sản phẩm
    @GetMapping("")
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "nhsproduct-list";
    }

    // Hiển thị form thêm
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("product", new NhsProduct());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "nhsproduct-form";
    }

    // Hiển thị form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Long id, Model model) {
        model.addAttribute("product",
                productService.findById(id).orElse(null)
        );
        model.addAttribute("categories", categoryService.getAllCategories());
        return "nhsproduct-form";
    }

    // Lưu mới
    @PostMapping("/create")
    public String saveProduct(@ModelAttribute("product") NhsProduct product) {
        productService.saveProduct(product);
        return "redirect:/nhs/products";
    }

    // Cập nhật
    @PostMapping("/update/{id}")
    public String update(@PathVariable Long id,
                         @ModelAttribute("product") NhsProduct product) {
        product.setNhsId(id);
        productService.saveProduct(product);
        return "redirect:/nhs/products";
    }

    // Xóa
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        productService.deleteProduct(id);
        return "redirect:/nhs/products";
    }
}
