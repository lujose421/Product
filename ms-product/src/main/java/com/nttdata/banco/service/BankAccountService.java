package com.nttdata.banco.service;

import com.nttdata.product.openapi.model.BankAccountDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankAccountService {

    Flux<BankAccountDTO> getAllBankAccounts();

    Mono<BankAccountDTO> createBankAccount(BankAccountDTO bankAccountDTO);

    Mono<BankAccountDTO> getBankAccountById(String bankAccountId);

    Mono<BankAccountDTO> updateBankAccount(String bankAccountId, BankAccountDTO bankAccountDTO);

    Mono<Void> deleteBankAccount(String bankAccountId);
    public Mono<Double> getBalance(String id);

}
