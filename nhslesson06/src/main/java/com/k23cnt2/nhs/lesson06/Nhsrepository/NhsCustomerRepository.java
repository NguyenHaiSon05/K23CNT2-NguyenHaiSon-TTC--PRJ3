package com.k23cnt2.nhs.lesson06.Nhsrepository;

import com.k23cnt2.nhs.lesson06.Nhsentity.NhsCustomer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NhsCustomerRepository extends JpaRepository<NhsCustomer, Long> {
}
