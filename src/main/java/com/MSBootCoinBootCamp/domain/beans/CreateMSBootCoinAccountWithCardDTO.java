package com.MSBootCoinBootCamp.domain.beans;

import lombok.*;

import javax.validation.constraints.NotBlank;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMSBootCoinAccountWithCardDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String cellphoneNumber;
    @NotBlank
    private String imei;
    @NotBlank
    private String docIdemType;
    @NotBlank
    private String docNum;
    @NotBlank
    private String debitCardNumber;
}
