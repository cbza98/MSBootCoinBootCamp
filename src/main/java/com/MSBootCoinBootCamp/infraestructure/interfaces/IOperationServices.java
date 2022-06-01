package com.MSBootCoinBootCamp.infraestructure.interfaces;

import com.MSBootCoinBootCamp.domain.beans.DebitCardBalanceDTO;
import com.MSBootCoinBootCamp.domain.model.DebitCard;
import com.MSBootCoinBootCamp.domain.model.Operacion;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IOperationServices {
    Flux<Operacion> findAll();
    Mono<Operacion> delete(String id);
    Mono<Operacion> findById(String id);
    Mono<Operacion> save(Operacion _Operacion);

}


