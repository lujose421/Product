package com.nttdata.banco.controller;

import com.nttdata.banco.openapi.api.CreditApi;
import com.nttdata.banco.openapi.model.Credit;
import com.nttdata.banco.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class CreditController implements CreditApi {
    @Autowired
    private CreditService creditService;

    @Override
    public Mono<ResponseEntity<Credit>> createCredit(
            @RequestBody Mono<Credit> credit, ServerWebExchange exchange) {
        return credit
                .flatMap(this.creditService::createCredit)
                .map(creditMAp-> ResponseEntity.status(HttpStatus.CREATED).body(creditMAp))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteCredit(
            @PathVariable String creditId, ServerWebExchange exchange) {
        return this.creditService.deleteCredit(creditId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public Mono<ResponseEntity<Flux<Credit>>> getAllCredits(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok().body(this.creditService.getAllCredits()));
    }

    @Override
    public Mono<ResponseEntity<Credit>> getCreditById(
            @PathVariable String creditId, ServerWebExchange exchange) {
        return this.creditService.getCreditById(creditId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public Mono<ResponseEntity<Credit>> updateCredit(
            @PathVariable String creditId,
            @RequestBody Mono<Credit> credit, ServerWebExchange exchange) {
        return credit
                .flatMap(creditMap->this.creditService.updateCredit(creditId,creditMap))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }


}
