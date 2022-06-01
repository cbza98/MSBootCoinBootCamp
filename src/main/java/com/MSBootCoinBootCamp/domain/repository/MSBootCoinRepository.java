package com.MSBootCoinBootCamp.domain.repository;
import com.MSBootCoinBootCamp.domain.model.MSBootCoinAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface MSBootCoinRepository extends ReactiveMongoRepository<MSBootCoinAccount, String> {
	Mono<Boolean> existsByCellphoneNumber(String cellphoneNumber);
	Mono<MSBootCoinAccount> findByCellphoneNumber(String cellphoneNumber);
/*
	Mono<Account> findFirstByAccountNumberInAndBalanceGreaterThanEqualOrderByDebitCardLinkDateAsc
					(Collection<String> accountNumberList, BigDecimal Balance);
*/
}
