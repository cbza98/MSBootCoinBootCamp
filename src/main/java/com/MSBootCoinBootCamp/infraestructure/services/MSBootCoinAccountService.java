package com.MSBootCoinBootCamp.infraestructure.services;


import com.MSBootCoinBootCamp.application.exception.EntityNotExistsException;
import com.MSBootCoinBootCamp.application.exception.ResourceNotCreatedException;
import com.MSBootCoinBootCamp.application.helpers.TextUtils;
import com.MSBootCoinBootCamp.domain.beans.AvailableAmountDTO;
import com.MSBootCoinBootCamp.domain.beans.CreateMSBootCoinAccountDTO;
import com.MSBootCoinBootCamp.domain.beans.CreateMSBootCoinAccountWithCardDTO;
import com.MSBootCoinBootCamp.domain.model.MSBootCoinAccount;
import com.MSBootCoinBootCamp.domain.model.Message;
import com.MSBootCoinBootCamp.domain.repository.MSBootCoinRepository;
import com.MSBootCoinBootCamp.infraestructure.interfaces.IMSBootCoinAccountService;
import com.MSBootCoinBootCamp.infraestructure.kafkaservices.DebitCardStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

@Service
public class MSBootCoinAccountService implements IMSBootCoinAccountService {

    //Attributes
    @Autowired
    private MSBootCoinRepository repository;

    @Autowired
    private DebitCardService debitCardService;

    @Autowired
    private DebitCardStream senddebit;

    // Crud
    @Override
    public Flux<MSBootCoinAccount> findAll() {
        return repository.findAll();
    }

    @Override
    public Mono<MSBootCoinAccount> delete(String Id) {
        return repository.findById(Id).flatMap(deleted -> repository.delete(deleted).then(Mono.just(deleted))).switchIfEmpty(Mono.error(new EntityNotExistsException()));
    }

    @Override
    public Mono<MSBootCoinAccount> findById(String id) {
        return repository.findById(id).switchIfEmpty(Mono.error(new EntityNotExistsException("Account doesn't exists")));
    }

    @Override
    public Flux<MSBootCoinAccount> saveAll(List<MSBootCoinAccount> a) {

        return repository.saveAll(a);
    }

    @Override
    public Mono<MSBootCoinAccount> update(MSBootCoinAccount _request) {

        return repository.findById(_request.getCellphoneNumber()).flatMap(a -> {
            a.setCellphoneNumber(_request.getCellphoneNumber());
            a.setValid(_request.getValid());
            a.setDocIdemType(_request.getDocIdemType());
            a.setDocNum(_request.getDocNum());
            a.setEmail(_request.getEmail());
            a.setValid(_request.getValid());
            a.setLinkedDebitCard(_request.getLinkedDebitCard());
            return repository.save(a);
        }).switchIfEmpty(Mono.error(new EntityNotExistsException()));
    }

    //Business Logic
    @Override
    public Mono<AvailableAmountDTO> getAvailableAmount(String cellphoneNumber) {
        return repository.findById(cellphoneNumber).switchIfEmpty(Mono.error(new EntityNotExistsException("Account doesn't exists"))).map(a -> AvailableAmountDTO.builder().cellphoneNumber(a.getCellphoneNumber()).availableAmount(a.getBalance()).build());
    }

    @Override
    public Mono<MSBootCoinAccount> findByCellphoneNumber(String cellphoneNumber) {
        return repository.findByCellphoneNumber(cellphoneNumber);
    }

    @Override
    public Mono<BigDecimal> updateBalanceSend(String id, String linkcard, BigDecimal balance) {
        Mono<BigDecimal> returnvaluesend = repository.findById(id).flatMap(a -> {
            BigDecimal bigDecimal = a.getBalance().subtract(balance);
            a.setBalance(bigDecimal);
            return repository.save(a).map(b -> b.getBalance());
        });

        if (!TextUtils.isNullOrEmpty(linkcard)) {
            Message M = Message.builder()
                    .amount(balance)
                    .referencia1(linkcard)
                    .build();
            senddebit.sendMessage(M);
        }

        return returnvaluesend;

    }

    @Override
    public Mono<BigDecimal> updateBalanceReceive(String id, String linkcardreceive, BigDecimal balance) {


        Mono<BigDecimal> returnvalue = repository.findById(id)
                .filter(a -> balance.compareTo(a.getBalance()) <= 0)
                .switchIfEmpty(Mono.error(new ResourceNotCreatedException("Withdrawal is more than actual balance")))
                .flatMap(a -> {

            a.setBalance(a.getBalance().add(balance));
            return repository.save(a).map(b -> b.getBalance());
        });

        if (!TextUtils.isNullOrEmpty(linkcardreceive)) {
            Message M = Message.builder()
                    .amount(balance)
                    .referencia1(linkcardreceive)
                    .build();
            senddebit.sendMessage(M);
        }


        return returnvalue;

    }

    @Override
    public Mono<MSBootCoinAccount> createMSBootCoinAccount(CreateMSBootCoinAccountDTO account) {

        Mono<Boolean> _bool = repository.existsByCellphoneNumber(account.getCellphoneNumber());

        return Mono.zip(_bool, Mono.just(account)).filter(t -> cellphoneValidation.test(t.getT1().booleanValue())).switchIfEmpty(Mono.error(new ResourceNotCreatedException("CellPhone has associate"))).flatMap(t -> mapToAccountAndSave.apply(t.getT2()));
    }

    private final Function<CreateMSBootCoinAccountDTO, Mono<MSBootCoinAccount>> mapToAccountAndSave = dto -> {

        MSBootCoinAccount a = MSBootCoinAccount.builder().valid(true).balance(new BigDecimal("0.00")).cellphoneNumber(dto.getCellphoneNumber()).docIdemType(dto.getDocIdemType()).docNum(dto.getDocNum()).imei(dto.getImei()).createdDate(LocalDate.now()).createdDateTime(LocalDateTime.now()).email(dto.getEmail()).build();
        return repository.save(a);
    };
    private final Function<CreateMSBootCoinAccountWithCardDTO, Mono<MSBootCoinAccount>> mapToAccountAndSaveWithCard = dto -> {

        MSBootCoinAccount a = MSBootCoinAccount.builder().valid(true).balance(new BigDecimal("0.00")).cellphoneNumber(dto.getCellphoneNumber()).docIdemType(dto.getDocIdemType()).docNum(dto.getDocNum()).imei(dto.getImei()).createdDate(LocalDate.now()).createdDateTime(LocalDateTime.now()).email(dto.getEmail()).linkedDebitCard(dto.getDebitCardNumber()).build();
        return repository.save(a);
    };
    private final Predicate<Boolean> cellphoneValidation = t -> t.equals(false);
    private final Predicate<Boolean> debitcardexist = t -> t.equals(true);

    private final Predicate<String> poseetarjeta = t ->{

      return (t.equals(!t.isBlank()) || t.equals(!t.isEmpty()));
    };
}
