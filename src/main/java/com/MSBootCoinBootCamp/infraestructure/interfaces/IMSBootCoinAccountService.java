package com.MSBootCoinBootCamp.infraestructure.interfaces;

import com.MSBootCoinBootCamp.domain.dtos.AvailableAmountDTO;
import com.MSBootCoinBootCamp.domain.dtos.CreateMSBootCoinAccountDTO;
import com.MSBootCoinBootCamp.domain.model.MSBootCoinAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.util.List;

public interface IMSBootCoinAccountService {
	Flux<MSBootCoinAccount> findAll();



	Mono<MSBootCoinAccount> createMSBootCoinAccount(CreateMSBootCoinAccountDTO account);

	Mono<MSBootCoinAccount> delete(String id);
	Mono<MSBootCoinAccount> findById(String id);
	Flux<MSBootCoinAccount> saveAll(List<MSBootCoinAccount> a);
	Mono<MSBootCoinAccount> update(MSBootCoinAccount request);
	//Mono<MSBootCoinAccount> findAllAccountsIn(Collection<String> accounts);
	Mono<BigDecimal> updateBalanceSend(String id, BigDecimal balance);
	Mono<BigDecimal> updateBalanceReceive(String id, BigDecimal balance);
	Mono<AvailableAmountDTO> getAvailableAmount(String accountNumber);
	Mono<MSBootCoinAccount> findByCellphoneNumber(String cellphoneNumber);
}
