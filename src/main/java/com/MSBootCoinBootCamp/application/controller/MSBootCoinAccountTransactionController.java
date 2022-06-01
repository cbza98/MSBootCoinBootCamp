package com.MSBootCoinBootCamp.application.controller;

import com.MSBootCoinBootCamp.domain.dtos.MSBootCoinOperationDTO;
import com.MSBootCoinBootCamp.infraestructure.services.MSBootCoinAccountTransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/BootCoin/Actions/BootCoinOperations")
public class MSBootCoinAccountTransactionController {
    @Autowired
    private MSBootCoinAccountTransactionService service;
    @PostMapping("/TransactionExec")
    public Mono<ResponseEntity<Map<String, Object>>> BootCoinPayment(@Valid @RequestBody Mono<MSBootCoinOperationDTO> request) {

        Map<String, Object> response = new HashMap<>();

        return request.flatMap(a -> service.doPayment(a).map(c -> {
            response.put("BootCoin Payment Operation", c);
            response.put("message", "Successful BootCoin Outgout Payment Transaction ");
            return ResponseEntity.created(URI.create("/YankiMobile/Actions/YankiOperations".concat(c.getTransactionId())))
                    .contentType(MediaType.APPLICATION_JSON).body(response);
        }));
    }
}
