package com.freshfruit.nhsentity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "coupons")
@Getter
@Setter
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer couponId;

    private String code;
    private Double discountPercent;

    private LocalDate startDate;
    private LocalDate endDate;

    private Boolean status;
}
