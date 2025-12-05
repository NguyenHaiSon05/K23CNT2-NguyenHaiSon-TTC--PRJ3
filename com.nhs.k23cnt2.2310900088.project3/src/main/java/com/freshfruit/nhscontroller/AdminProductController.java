package com.freshfruit.nhscontroller;

import com.freshfruit.nhsentity.Product;
import com.freshfruit.nhsservice.ProductService;
import com.freshfruit.nhsservice.CategoryService;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    private final ProductService productService;
    private final CategoryService categoryService;

    public AdminProductController(ProductService productService,
                                  CategoryService categoryService) {
        this.productService = productService;
        this.categoryService = categoryService;
    }

    // 1. Danh sách sản phẩm
    @GetMapping
    public String listProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/product/list";
    }

    // 2. Form thêm
    @GetMapping("/add")
    public String addProductForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/product/add";
    }

    // 3. Lưu
    @PostMapping("/save")
    public String saveProduct(@ModelAttribute Product product,
                              @RequestParam("imageFile") MultipartFile file) throws Exception {

        if (!file.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String uploadDir = "src/main/resources/static/uploads/";
            com.freshfruit.nhsutil.FileUploadUtil.saveFile(uploadDir, fileName, file);
            product.setImage("/uploads/" + fileName);
        }

        productService.saveProduct(product);
        return "redirect:/admin/products";
    }


    // 4. Form sửa
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable Integer id, Model model) {
        model.addAttribute("product", productService.getProductById(id));
        model.addAttribute("categories", categoryService.getAllCategories());
        return "admin/product/edit";
    }

    // 5. Cập nhật
    @PostMapping("/update")
    public String updateProduct(@ModelAttribute Product product,
                                @RequestParam("imageFile") MultipartFile file) throws Exception {

        if (!file.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
            String uploadDir = "src/main/resources/static/uploads/";
            com.freshfruit.nhsutil.FileUploadUtil.saveFile(uploadDir, fileName, file);
            product.setImage("/uploads/" + fileName);
        }

        productService.saveProduct(product);
        return "redirect:/admin/products";
    }


    // 6. Xóa
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Integer id) {
        productService.deleteProduct(id);
        return "redirect:/admin/products";
    }
}
