package com.freshfruit.nhsentity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;


@Entity
@Table(name = "reviews")
@Getter @Setter
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer rating; // 1–5

    private String comment;

    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
