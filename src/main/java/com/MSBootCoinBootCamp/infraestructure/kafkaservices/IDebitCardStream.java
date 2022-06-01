package com.MSBootCoinBootCamp.infraestructure.kafkaservices;

import com.MSBootCoinBootCamp.domain.beans.DebitCardDTO;
import com.MSBootCoinBootCamp.domain.model.DebitCard;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface IDebitCardStream {
    Mono<DebitCardDTO> findById(String debitCardNumber);

    Mono<DebitCard> doCardWithdraw(String debitCardNumber, BigDecimal amount);

    Mono<DebitCard> doCardDeposit(String debitCardNumber, BigDecimal amount);
}
