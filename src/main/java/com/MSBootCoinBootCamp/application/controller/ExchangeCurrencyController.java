package com.MSBootCoinBootCamp.application.controller;

import com.MSBootCoinBootCamp.domain.model.Operacion;
import com.MSBootCoinBootCamp.domain.model.TipoCambio;
import com.MSBootCoinBootCamp.infraestructure.services.ExchangeRateService;
import com.MSBootCoinBootCamp.infraestructure.services.OperationServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/BootCoin/Entities/ExchangeRate")
public class ExchangeCurrencyController {

    @Autowired
    private ExchangeRateService service;
    @GetMapping
    public Mono<ResponseEntity<Flux<TipoCambio>>> findAll() {
        return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll()));
    }
    @GetMapping("/{id}")
    public Mono<TipoCambio> findById(@PathVariable String id) {
        return service.findById(id);
    }
    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> createAccount(@Valid @RequestBody Mono<TipoCambio> request) {

        Map<String, Object> response = new HashMap<>();

        return request.flatMap(a -> service.save(a).map(c -> {
            response.put("Exchange Rate", c);
            response.put("Message", "Exchange Rate Successfully");
            return ResponseEntity.created(URI.create("/BootCoin/Entities/ExchangeRate/".concat(c.getCodigocambio())))
                    .contentType(MediaType.APPLICATION_JSON).body(response);
        }));
    }

}

