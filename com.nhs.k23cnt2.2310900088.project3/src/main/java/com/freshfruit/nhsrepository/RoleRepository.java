package com.freshfruit.nhsrepository;

import com.freshfruit.nhsentity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByRoleName(String name);
}
