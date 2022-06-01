package com.MSBootCoinBootCamp.domain.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateDebitCardDTO {
    @NotBlank
    private String codeBusinessPartner;
    @NotBlank
    private String accountNumber;
}
