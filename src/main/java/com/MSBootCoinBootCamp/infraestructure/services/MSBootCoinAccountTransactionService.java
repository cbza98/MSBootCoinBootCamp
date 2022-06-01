package com.MSBootCoinBootCamp.infraestructure.services;


import com.MSBootCoinBootCamp.application.exception.EntityNotExistsException;
import com.MSBootCoinBootCamp.application.exception.ResourceSpecificException;
import com.MSBootCoinBootCamp.domain.dtos.MSBootCoinOperationDTO;
import com.MSBootCoinBootCamp.domain.enums.TransactionType;
import com.MSBootCoinBootCamp.domain.model.MSBootCoinAccount;
import com.MSBootCoinBootCamp.domain.model.Transaction;
import com.MSBootCoinBootCamp.domain.repository.TransactionRepository;
import com.MSBootCoinBootCamp.infraestructure.broker.Accountbroker;
import com.MSBootCoinBootCamp.infraestructure.interfaces.IMSBootCoinAccountTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Function;

@Service
public class MSBootCoinAccountTransactionService implements IMSBootCoinAccountTransactionService {

    //Services and Repositories
    @Autowired
    TransactionRepository tRepository;
    @Autowired
    MSBootCoinAccountService accountService;
    @Autowired
    Accountbroker accountbroker;
    //Crud
    @Override
    public Flux<Transaction> findAll() {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Mono<Transaction> delete(String id) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Mono<Transaction> findById(String id) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Mono<ResponseEntity<Transaction>> update(String id, Transaction request) {
        // TODO Auto-generated method stub
        return null;
    }
    @Override
    public Flux<Transaction> saveAll(List<Transaction> a) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Mono<Transaction> doPayment(MSBootCoinOperationDTO operationDTO) {
        Mono<MSBootCoinAccount> fromAccount = accountService.findByCellphoneNumber(operationDTO.getFromCellphoneAccount())
                .switchIfEmpty(Mono.error(new EntityNotExistsException("Origin phone number doesn't exists")));

        Mono<MSBootCoinAccount> toAccount = accountService.findByCellphoneNumber(operationDTO.getFromCellphoneAccount())
                .switchIfEmpty(Mono.error(new EntityNotExistsException("Destiny phone number doesn't exists")));


        return Mono.zip(fromAccount,toAccount)
                .filter(a-> !(operationDTO.getFromCellphoneAccount().equals(operationDTO.getToCellphoneAccount())))
                .switchIfEmpty(Mono.error(new ResourceSpecificException("Phone number cannot be the same")))

                .flatMap(a-> accountService.updateBalanceReceive(a.getT1().getCellphoneNumber(), operationDTO.getAmount())

                        .thenReturn(a))
                .flatMap(a-> accountService.updateBalanceSend(a.getT2().getCellphoneNumber() ,operationDTO.getAmount())
                        .thenReturn(a))
                .then(Mono.just(operationDTO))
                .flatMap(r-> savedoPayment.apply(r))
                .switchIfEmpty(Mono.error(new ResourceSpecificException("Transaction error")));
    }

    private final Function<MSBootCoinOperationDTO, Mono<Transaction>> savedoPayment = dto -> {


        Transaction t = Transaction.builder()
                .amount(dto.getAmount())
                .fromCellphoneAccount(dto.getFromCellphoneAccount())
                .transactiontype(TransactionType.PAYMENT)
                .toCellphoneAccount(dto.getToCellphoneAccount())
                .createDate(LocalDate.now()).build();

        return tRepository.save(t);
    };


}






