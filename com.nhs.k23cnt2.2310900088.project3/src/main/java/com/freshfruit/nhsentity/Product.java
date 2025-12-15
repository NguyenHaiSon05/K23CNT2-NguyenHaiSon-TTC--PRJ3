package com.freshfruit.nhsentity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"category", "cartItems", "orderItems", "reviews"})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productId;

    private String unit;
    private String name;
    private Double price;
    private String image;
    private String imageFolder;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer stock;

    @Column(name = "sold")
    private Integer sold = 0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<CartItem> cartItems;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
