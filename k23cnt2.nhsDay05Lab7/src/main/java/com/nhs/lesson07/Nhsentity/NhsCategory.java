package com.nhs.lesson07.Nhsentity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Table(name = "nhs_categories")
@Data
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class NhsCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nhs_id")
    Long nhsId;

    @Column(name = "nhs_category_name")
    String nhsCategoryName;

    @Column(name = "nhs_category_status")
    Boolean nhsCategoryStatus;
}
