package com.k23cnt2.nhs.lesson06.Nhsentity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "nhs_customer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NhsCustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long nhsId;

    private String nhsUsername;
    private String nhsPassword;
    private String nhsFullName;
    private String nhsAddress;
    private String nhsPhone;
    private String nhsEmail;
    private String nhsBirthDay;
    private Boolean nhsActive;
}
