package com.freshfruit.nhsservice;

import com.freshfruit.nhsentity.Category;
import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    Category getCategoryById(Integer id);
    Category saveCategory(Category category);
    void deleteCategory(Integer id);
}
