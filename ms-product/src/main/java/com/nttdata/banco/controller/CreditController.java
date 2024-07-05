package com.nttdata.banco.controller;

import com.nttdata.banco.service.CreditService;
import com.nttdata.product.openapi.api.CreditApi;
import com.nttdata.product.openapi.model.CreditDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(CreditController.class);

    @Override
    public Mono<ResponseEntity<Flux<CreditDTO>>> getAllCredits(ServerWebExchange exchange) {
        logger.info("endpooint getAllCredit - ini ");
        return Mono.just(ResponseEntity.ok().body(this.creditService.getAllCredits()))
                .doOnTerminate(() -> logger.info("endpoint - getAllCredits - end"));
    }


    @Override
    public Mono<ResponseEntity<CreditDTO>> getCreditById(
            @PathVariable String creditId, ServerWebExchange exchange) {
        logger.info("endpoint getCreditById - ini");
        return this.creditService.getCreditById(creditId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build())
                .doOnTerminate(() -> logger.info("endpoint getCreditById - end"));
    }

    @Override
    public Mono<ResponseEntity<CreditDTO>> createCredit(
            @RequestBody Mono<CreditDTO> credit, ServerWebExchange exchange) {
        logger.info("endpoint createCredit - ini");
        return credit
                .flatMap(this.creditService::createCredit)
                .map(creditMAp -> ResponseEntity.status(HttpStatus.CREATED).body(creditMAp))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build())
                .doOnTerminate(() -> logger.info("endpoint createCredit - end"));
    }


    @Override
    public Mono<ResponseEntity<CreditDTO>> updateCredit(
            @PathVariable String creditId,
            @RequestBody Mono<CreditDTO> creditDTO, ServerWebExchange exchange) {
        logger.info("endpoint updateCredit - ini");
        return creditDTO
                .flatMap(creditMapper -> creditService.updateCredit(creditId, creditMapper))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build())
                .doOnTerminate(() -> logger.info("endpoint updateBankAccount - end"));
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteCredit(
            @PathVariable String creditId, ServerWebExchange exchange) {
        logger.info("endpoint deleteCredit - ini");
        return this.creditService.deleteCredit(creditId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build())
                .doOnTerminate(() -> logger.info("endpoint deleteCredit -end"));
    }
}
