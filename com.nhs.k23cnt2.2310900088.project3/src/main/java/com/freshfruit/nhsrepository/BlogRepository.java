package com.freshfruit.nhsrepository;

import com.freshfruit.nhsentity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog, Integer> {
}
