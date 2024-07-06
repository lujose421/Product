package com.nttdata.banco.controller;

import com.nttdata.banco.service.TransactionService;
import com.nttdata.product.openapi.api.TransactionApi;
import com.nttdata.product.openapi.model.TransactionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
public class TransactionController implements TransactionApi {

    @Autowired
    private TransactionService transactionService;

    private static final Logger logger = LoggerFactory.getLogger(CreditController.class);

    @Override
    public Mono<ResponseEntity<TransactionDTO>> createTransaction(
            @RequestBody Mono<TransactionDTO> transactionDTO, ServerWebExchange exchange) {
        logger.info("endpoint createTransaction - ini");
        return transactionDTO
                .flatMap(this.transactionService::createTransaction)
                .map(transactionMap -> ResponseEntity.status(HttpStatus.CREATED).body(transactionMap))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build())
                .doOnTerminate(() -> logger.info("endpoint cretaTransacction - end"));
    }

    @Override
    public Mono<ResponseEntity<TransactionDTO>> getTransactionById(
            @PathVariable String numOperacion, ServerWebExchange exchange) {
        logger.info("endpoint getTransactionById - ini");
        return this.transactionService.getTransactionFindByNumOperacion(numOperacion)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build())
                .doOnTerminate(() -> logger.info("endpoint getTransactionById - end"));
    }
}
