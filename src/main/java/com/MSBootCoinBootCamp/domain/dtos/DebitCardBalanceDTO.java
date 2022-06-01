package com.MSBootCoinBootCamp.domain.dtos;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DebitCardBalanceDTO {
    private String debitCardNumber;
    private String accountNumber;
    private BigDecimal balance;
}
