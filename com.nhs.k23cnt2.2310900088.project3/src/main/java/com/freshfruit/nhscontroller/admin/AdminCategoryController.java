package com.freshfruit.nhscontroller.admin;

import com.freshfruit.nhsentity.Category;
import com.freshfruit.nhsrepository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    // 1. HIỂN THỊ DANH SÁCH DANH MỤC
    @GetMapping
    public String listCategories(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/category/list";
    }

    // 2. FORM THÊM DANH MỤC
    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category/create";
    }

    // 3. LƯU DANH MỤC
    @PostMapping("/save")
    public String saveCategory(@ModelAttribute Category category) {
        categoryRepository.save(category);
        return "redirect:/admin/categories";
    }

    // 4. FORM SỬA DANH MỤC
    @GetMapping("/edit/{id}")
    public String editCategory(@PathVariable Integer id, Model model) {
        Category category = categoryRepository.findById(id).orElse(null);
        model.addAttribute("category", category);
        return "admin/category/edit";
    }

    // 5. XÓA DANH MỤC
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Integer id) {
        categoryRepository.deleteById(id);
        return "redirect:/admin/categories";
    }

}
