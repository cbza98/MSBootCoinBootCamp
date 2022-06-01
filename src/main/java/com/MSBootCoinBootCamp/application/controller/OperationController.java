package com.MSBootCoinBootCamp.application.controller;

import com.MSBootCoinBootCamp.domain.model.Operacion;
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
@RequestMapping("/BootCoin/Entities/Operation")
public class OperationController {
    @Autowired
    private OperationServices service;
    @GetMapping
    public Mono<ResponseEntity<Flux<Operacion>>> findAll() {
        return Mono.just(ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(service.findAll()));
    }
    @GetMapping("/{id}")
    public Mono<Operacion> findById(@PathVariable String id) {
        return service.findById(id);
    }
    @PostMapping
    public Mono<ResponseEntity<Map<String, Object>>> create(@Valid @RequestBody Mono<Operacion> request) {

        Map<String, Object> response = new HashMap<>();

        return request.flatMap(a -> service.save(a).map(c -> {
            response.put("Operation", c);
            response.put("Message", "Operation created Successfully");
            return ResponseEntity.created(URI.create("/BootCoin/Entities/Operation/".concat(c.getCodigooperacion())))
                    .contentType(MediaType.APPLICATION_JSON).body(response);
        }));
    }

}
