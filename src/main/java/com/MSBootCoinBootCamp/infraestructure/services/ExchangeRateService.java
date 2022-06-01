package com.MSBootCoinBootCamp.infraestructure.services;

import com.MSBootCoinBootCamp.application.exception.EntityNotExistsException;
import com.MSBootCoinBootCamp.domain.model.Operacion;
import com.MSBootCoinBootCamp.domain.model.TipoCambio;
import com.MSBootCoinBootCamp.domain.repository.ExchangeRateRepository;
import com.MSBootCoinBootCamp.domain.repository.OperationRepository;
import com.MSBootCoinBootCamp.infraestructure.interfaces.IExchangeRateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Service
public class ExchangeRateService implements IExchangeRateService {
    @Autowired
    ExchangeRateRepository repository;
    @Override
    public Flux<TipoCambio> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<TipoCambio> delete(String id) {
        return repository.findById(id).flatMap(delete->  repository.delete(delete)
                .then(Mono.just(delete)).switchIfEmpty(Mono.error(new EntityNotExistsException())));
    }

    @Override
    public Mono<TipoCambio> findById(String id) {
        return findById(id);
    }

    @Override
    public Mono<TipoCambio> save(TipoCambio _TipoCambio) {
        _TipoCambio.setFechacambio(LocalDate.now());
        return repository.save(_TipoCambio);
    }
}
