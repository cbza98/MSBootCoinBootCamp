package com.MSBootCoinBootCamp.domain.dtos;

import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateMSBootCoinAccountDTO {
    @NotBlank
    private String email;
    @NotBlank
    private String cellphoneNumber;
    @NotBlank
    private String docuidentype;
    @NotBlank
    private String docuidentidad;
}

