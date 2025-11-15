package com.k23cnt2.nhs.lesson06.Nhsdto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class NhsCustomerDTO {

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
