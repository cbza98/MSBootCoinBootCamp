package com.MSBootCoinBootCamp.infraestructure.broker;

import com.MSBootCoinBootCamp.domain.dtos.DebitCardDTO;
import com.MSBootCoinBootCamp.domain.model.DebitCard;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface IAccountbroker {
    Mono<DebitCardDTO> findById(String debitCardNumber);

    Mono<DebitCard> doCardWithdraw(String debitCardNumber, BigDecimal amount);

    Mono<DebitCard> doCardDeposit(String debitCardNumber, BigDecimal amount);
}
