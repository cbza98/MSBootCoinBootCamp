package com.MSBootCoinBootCamp.infraestructure.interfaces;

import com.MSBootCoinBootCamp.domain.model.TipoCambio;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IExchangeRateService {


    Flux<TipoCambio> findAll();
    Mono<TipoCambio> delete(String id);
    Mono<TipoCambio> findById(String id);
    Mono<TipoCambio> save(TipoCambio _TipoCambio);
}

