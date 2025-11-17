package com.nhs.lesson07.Nhsentity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "nhs_products")
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NhsProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nhs_id")
    Long nhsId;

    @Column(name = "nhs_name")
    String nhsName;

    @Column(name = "nhs_image_url")
    String nhsImageUrl;

    @Column(name = "nhs_quantity")
    Integer nhsQuantity;

    @Column(name = "nhs_price")
    Double nhsPrice;

    @Column(name = "nhs_content")
    String nhsContent;

    @Column(name = "nhs_status")
    Boolean nhsStatus;

    @ManyToOne
    @JoinColumn(name = "nhs_category_id", nullable = false)
    NhsCategory category;
}
