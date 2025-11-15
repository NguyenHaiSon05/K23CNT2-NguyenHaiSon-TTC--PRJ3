package com.k23cnt2.nhs.lesson06.Nhsdto;

import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class NhsStudentDTO {

    private long nhsId;
    private String nhsName;
    private String nhsEmail;
    private int nhsAge;

}
