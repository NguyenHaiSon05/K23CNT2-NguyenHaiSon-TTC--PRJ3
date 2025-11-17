package com.nhs.lesson07.Nhscontroller;

import com.nhs.lesson07.Nhsentity.NhsCategory;
import com.nhs.lesson07.Nhsservice.NhsCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nhs/category")
public class NhsCategoryController {

    @Autowired
    private NhsCategoryService nhsCategoryService;

    // Danh sách category
    @GetMapping("")
    public String listCategories(Model model) {
        model.addAttribute("categories", nhsCategoryService.getAllCategories());
        return "nhscategory-list";
    }

    // Hiển thị form thêm mới
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new NhsCategory());
        return "nhscategory-form";
    }

    // Hiển thị form sửa
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        model.addAttribute("category",
                nhsCategoryService.getCategoryById(id).orElse(null));
        return "nhscategory-form";
    }

    // Lưu category (create)
    @PostMapping("/create")
    public String saveCategory(@ModelAttribute("category") NhsCategory category) {
        nhsCategoryService.saveCategory(category);
        return "redirect:/nhs/category";
    }

    // Cập nhật category
    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable Long id,
                                 @ModelAttribute("category") NhsCategory category) {
        category.setNhsId(id);
        nhsCategoryService.saveCategory(category);
        return "redirect:/nhs/category";
    }

    // Xóa category
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        nhsCategoryService.deleteCategory(id);
        return "redirect:/nhs/category";
    }
}
