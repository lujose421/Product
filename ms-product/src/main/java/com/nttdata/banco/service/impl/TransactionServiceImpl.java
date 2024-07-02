package com.nttdata.banco.service.impl;

import com.nttdata.banco.service.TransactionService;
import com.nttdata.product.openapi.model.TransactionDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TransactionServiceImpl implements TransactionService {
    @Override
    public Mono<TransactionDTO> createTransaction(TransactionDTO transactionDTO) {
        return null;
    }

    @Override
    public Mono<TransactionDTO> getTransactionFindByNumOperacion(String numOperacion) {
        return null;
    }
}
