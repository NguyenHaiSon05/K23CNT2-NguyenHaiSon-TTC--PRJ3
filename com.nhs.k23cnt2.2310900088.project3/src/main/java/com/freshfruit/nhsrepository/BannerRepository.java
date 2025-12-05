package com.freshfruit.nhsrepository;

import com.freshfruit.nhsentity.Banner;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BannerRepository extends JpaRepository<Banner, Integer> {
}
