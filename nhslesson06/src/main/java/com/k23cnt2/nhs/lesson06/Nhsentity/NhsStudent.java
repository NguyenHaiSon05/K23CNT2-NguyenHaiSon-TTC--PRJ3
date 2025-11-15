package com.k23cnt2.nhs.lesson06.Nhsentity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NhsStudent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long nhsId;

    private String nhsName;

    private String nhsEmail;

    private int nhsAge;
}
