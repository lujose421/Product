package com.nttdata.banco.service;

import com.nttdata.product.openapi.model.TransactionDTO;
import reactor.core.publisher.Mono;

public interface TransactionService {
    public Mono<TransactionDTO> createTransaction(TransactionDTO transactionDTO);

    public Mono<TransactionDTO> getTransactionFindByNumOperacion(String numOperacion);

}
