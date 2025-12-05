package com.freshfruit.nhsrepository;

import com.freshfruit.nhsentity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
