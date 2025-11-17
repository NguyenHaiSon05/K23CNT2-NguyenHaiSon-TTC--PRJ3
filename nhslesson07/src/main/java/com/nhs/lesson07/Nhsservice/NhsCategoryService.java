package com.nhs.lesson07.Nhsservice;

import com.nhs.lesson07.Nhsentity.NhsCategory;
import com.nhs.lesson07.Nhsrepository.NhsCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NhsCategoryService {

    @Autowired
    private NhsCategoryRepository nhsCategoryRepository;

    // Lấy danh sách category
    public List<NhsCategory> getAllCategories() {
        return nhsCategoryRepository.findAll();
    }

    // Lấy category theo id
    public Optional<NhsCategory> getCategoryById(Long id) {
        return nhsCategoryRepository.findById(id);
    }

    // Tạo hoặc cập nhật category
    public NhsCategory saveCategory(NhsCategory category) {
        return nhsCategoryRepository.save(category);
    }

    // Xóa theo id
    public void deleteCategory(Long id) {
        nhsCategoryRepository.deleteById(id);
    }
}
