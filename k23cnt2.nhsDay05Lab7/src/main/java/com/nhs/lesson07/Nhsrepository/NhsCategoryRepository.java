package com.nhs.lesson07.Nhsrepository;

import com.nhs.lesson07.Nhsentity.NhsCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhsCategoryRepository extends JpaRepository<NhsCategory, Long> {
}
