package com.MSBootCoinBootCamp.infraestructure.interfaces;

import com.MSBootCoinBootCamp.domain.beans.MSBootCoinOperationDTO;
import com.MSBootCoinBootCamp.domain.model.Transaction;

import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IMSBootCoinAccountTransactionService {
	
	Flux<Transaction> findAll();
	
	Mono<Transaction> delete(String id);

	Mono<Transaction> findById(String id);
	
	Mono<ResponseEntity<Transaction>> update(String id, Transaction request);
	
	Flux<Transaction> saveAll(List<Transaction> a);



	Mono<Transaction> doPayment(MSBootCoinOperationDTO operationDTO);

	Mono<Transaction> doReceive(MSBootCoinOperationDTO operationDTO);

}
