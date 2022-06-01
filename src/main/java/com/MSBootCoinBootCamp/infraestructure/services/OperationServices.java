package com.MSBootCoinBootCamp.infraestructure.services;

import com.MSBootCoinBootCamp.application.exception.EntityNotExistsException;
import com.MSBootCoinBootCamp.domain.model.Operacion;
import com.MSBootCoinBootCamp.domain.repository.OperationRepository;
import com.MSBootCoinBootCamp.domain.repository.TransactionRepository;
import com.MSBootCoinBootCamp.infraestructure.interfaces.IOperationServices;
import org.apache.kafka.common.protocol.types.Field;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
@Service
public class OperationServices implements IOperationServices {
    @Autowired
    OperationRepository repository;
    @Override
    public Flux<Operacion> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<Operacion> delete(String id) {
       return repository.findById(id).flatMap(delete->  repository.delete(delete)
               .then(Mono.just(delete)).switchIfEmpty(Mono.error(new EntityNotExistsException())));
    }

    @Override
    public Mono<Operacion> findById(String id) {
        return findById(id);
    }

    @Override
    public Mono<Operacion> save(Operacion _Operacion) {
        return repository.save(_Operacion);
    }
}
