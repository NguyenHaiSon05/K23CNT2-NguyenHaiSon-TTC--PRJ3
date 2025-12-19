package com.freshfruit.nhscontroller.admin;

import com.freshfruit.nhsentity.Category;
import com.freshfruit.nhsrepository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/admin/categories")
public class AdminCategoryController {

    private final CategoryRepository categoryRepository;

    // 1️⃣ DANH SÁCH DANH MỤC
    @GetMapping
    public String list(Model model) {
        model.addAttribute("categories", categoryRepository.findAll());
        return "admin/category/list";
    }

    // 2️⃣ FORM THÊM DANH MỤC
    @GetMapping("/create")
    public String create(Model model) {
        model.addAttribute("category", new Category());
        return "admin/category/form";
    }

    // 3️⃣ FORM SỬA DANH MỤC
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model) {
        Category category = categoryRepository.findById(id).orElse(null);

        if (category == null) {
            return "redirect:/admin/categories";
        }

        model.addAttribute("category", category);
        return "admin/category/form";
    }

    // 4️⃣ LƯU (CẢ THÊM + SỬA)
    @PostMapping("/save")
    public String save(@ModelAttribute Category category) {
        categoryRepository.save(category);
        return "redirect:/admin/categories";
    }

    // 5️⃣ XÓA DANH MỤC
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Integer id) {
        categoryRepository.deleteById(id);
        return "redirect:/admin/categories";
    }
}
