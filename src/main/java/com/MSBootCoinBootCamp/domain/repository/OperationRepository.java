package com.MSBootCoinBootCamp.domain.repository;

import com.MSBootCoinBootCamp.domain.model.Operacion;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OperationRepository extends ReactiveMongoRepository<Operacion, String> {

}
