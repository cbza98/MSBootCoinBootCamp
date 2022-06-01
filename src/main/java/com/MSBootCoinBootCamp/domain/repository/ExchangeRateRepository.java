package com.MSBootCoinBootCamp.domain.repository;

import com.MSBootCoinBootCamp.domain.model.TipoCambio;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ExchangeRateRepository extends ReactiveMongoRepository<TipoCambio, String> {
}
