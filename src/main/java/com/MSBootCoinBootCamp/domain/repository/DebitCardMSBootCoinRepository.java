package com.MSBootCoinBootCamp.domain.repository;

import com.MSBootCoinBootCamp.domain.model.DebitCard;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface DebitCardMSBootCoinRepository extends ReactiveMongoRepository<DebitCard,String> {
    Mono<DebitCard> findByCardNumberAndExpiringDateAndCvv(String creditCardNumber, String expDate, String cvv);
    Mono<Boolean> existsByCardNumber(String CardNumber);
    Mono<DebitCard> findByCardNumber(String CardNumber);
}
