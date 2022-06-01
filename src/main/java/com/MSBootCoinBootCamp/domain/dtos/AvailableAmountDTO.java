package com.MSBootCoinBootCamp.domain.dtos;

import lombok.*;

import java.math.BigDecimal;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AvailableAmountDTO {
    private String cellphoneNumber;

    private BigDecimal availableAmount;
}
