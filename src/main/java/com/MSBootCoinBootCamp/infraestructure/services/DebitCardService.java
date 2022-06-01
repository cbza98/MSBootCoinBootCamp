package com.MSBootCoinBootCamp.infraestructure.services;

import com.MSBootCoinBootCamp.application.exception.EntityNotExistsException;
import com.MSBootCoinBootCamp.domain.beans.DebitCardBalanceDTO;
import com.MSBootCoinBootCamp.domain.model.DebitCard;
import com.MSBootCoinBootCamp.domain.repository.DebitCardMSBootCoinRepository;
import com.MSBootCoinBootCamp.infraestructure.interfaces.IDebitCardService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@Slf4j
@Service
public class DebitCardService implements IDebitCardService {

    @Autowired
    private DebitCardMSBootCoinRepository repository;

    @Override
    public Flux<DebitCard> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<DebitCard> createDebitCard(DebitCard debitCardDTO) {
        return repository.save(debitCardDTO);
    }


    @Override
    public Mono<DebitCard> delete(String id) {
        return repository.findById(id).flatMap(deleted -> repository.delete(deleted).then(Mono.just(deleted)))
                .switchIfEmpty(Mono.error(new EntityNotExistsException()));
    }

    @Override
    public Mono<DebitCard> findById(String id) {
        return repository.findById(id)
                .switchIfEmpty(Mono.error(new EntityNotExistsException("Debit Card doesn't exists")));
    }

    @Override
    public Mono<DebitCardBalanceDTO> getDebitCardBalance(String debitCardNumber) {
        return null;
    }

    @Override
    public Mono<Boolean> existsByCardNumber(String CardNumber) {
        return repository.existsByCardNumber(CardNumber);
    }

    public void updateaddbalance(String CardNumber,BigDecimal amount)
    {
         repository.findByCardNumber(CardNumber).flatMap(r->{r.setAmount(amount);
         repository.save(r);
        return null;});
    }

    public void updatesubstractbalance(String CardNumber, BigDecimal amount) {
        repository.findByCardNumber(CardNumber).flatMap(r->{r.setAmount(amount);
            repository.save(r);
            return null;});
    }



}
