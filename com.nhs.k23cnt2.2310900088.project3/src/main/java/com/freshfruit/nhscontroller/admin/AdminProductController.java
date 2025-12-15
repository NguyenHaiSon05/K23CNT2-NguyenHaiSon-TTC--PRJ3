package com.freshfruit.nhscontroller.admin;

import com.freshfruit.nhsentity.Category;
import com.freshfruit.nhsentity.Product;
import com.freshfruit.nhsservice.CategoryService;
import com.freshfruit.nhsservice.ProductService;
import com.freshfruit.nhsutil.FileUploadUtil;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;


@Controller
@RequestMapping("/admin/products")
public class AdminProductController {

    private final ProductService productService;
    private final CategoryService categoryService; // ✅ THÊM DÒNG NÀY

    public AdminProductController(ProductService productService,
                                  CategoryService categoryService) { // ✅ THÊM
        this.productService = productService;
        this.categoryService = categoryService;
    }

    // 1️⃣ Danh sách sản phẩm
    @GetMapping
    public String list(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "admin/product/list";
    }

    // 2️⃣ Form thêm
    @GetMapping("/create")
    public String createForm(Model model) {
        model.addAttribute("product", new Product());
        model.addAttribute("categories", categoryService.getAll());
        return "admin/product/form";
    }

    // 3️⃣ Form sửa
    @GetMapping("/edit/{id}")
    public String editForm(@PathVariable Integer id, Model model) {
        Product product = productService.getById(id);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService.getAll());
        return "admin/product/form";
    }

    // 4️⃣ Lưu
    @PostMapping("/save")
    public String save(
            @ModelAttribute Product product,
            @RequestParam("categoryId") Integer categoryId,
            @RequestParam("imageFile") MultipartFile imageFile,
            Model model
    ) throws Exception {

        Category category = categoryService.getById(categoryId);
        product.setCategory(category);

        // 👉 Lấy product cũ nếu đang sửa
        Product existingProduct = null;
        if (product.getProductId() != null) {
            existingProduct = productService.getById(product.getProductId());
        }

        // ===============================
        // ✅ NẾU CÓ UPLOAD ẢNH MỚI
        // ===============================
        if (imageFile != null && !imageFile.isEmpty()) {

            // 1️⃣ Validate dung lượng (≤ 2MB)
            long maxSize = 2 * 1024 * 1024;
            if (imageFile.getSize() > maxSize) {
                model.addAttribute("error", "Ảnh không được vượt quá 2MB");
                model.addAttribute("categories", categoryService.getAll());
                return "admin/product/form";
            }

            // 2️⃣ Validate định dạng
            String contentType = imageFile.getContentType();
            if (contentType == null ||
                    !(contentType.equals("image/jpeg")
                            || contentType.equals("image/png")
                            || contentType.equals("image/webp"))) {

                model.addAttribute("error", "Chỉ cho phép ảnh JPG, PNG hoặc WEBP");
                model.addAttribute("categories", categoryService.getAll());
                return "admin/product/form";
            }

            // 3️⃣ XÓA ẢNH CŨ (chỉ khi đang sửa + có ảnh mới)
            if (existingProduct != null && existingProduct.getImage() != null) {

                // DB đang lưu: products/gioquatraicay/abc.png
                String oldImagePath = "G:/Project3/" + existingProduct.getImage();

                File oldFile = new File(oldImagePath);
                if (oldFile.exists()) {
                    oldFile.delete();
                }
            }


            // 4️⃣ Map category → folder
            String folder = switch (category.getCategoryId()) {
                case 1 -> "traicaynoidia";
                case 2 -> "traicaynhapkhau";
                case 3 -> "gioquatraicay";
                case 4 -> "traicaykho";
                default -> "others";
            };

            // 5️⃣ Upload ảnh mới
            String uploadDir = "G:/Project3/products/" + folder;
            String fileName = FileUploadUtil.saveFile(uploadDir, imageFile);

            product.setImage("/uploads/products/" + folder + "/" + fileName);

        } else {
            // ===============================
            // ❗ KHÔNG CHỌN ẢNH → GIỮ ẢNH CŨ
            // ===============================
            if (existingProduct != null) {
                product.setImage(existingProduct.getImage());
            }
        }

        productService.save(product);
        return "redirect:/admin/products";
    }








    // 5️⃣ Xoá
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        productService.delete(id);
        return "redirect:/admin/products";
    }
}
