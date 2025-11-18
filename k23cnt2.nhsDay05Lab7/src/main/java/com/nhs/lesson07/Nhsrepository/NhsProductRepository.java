package com.nhs.lesson07.Nhsrepository;

import com.nhs.lesson07.Nhsentity.NhsProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhsProductRepository extends JpaRepository<NhsProduct, Long> {
}
