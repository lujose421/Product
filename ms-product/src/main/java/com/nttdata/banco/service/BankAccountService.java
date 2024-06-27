package com.nttdata.banco.service;

import com.nttdata.banco.openapi.model.BankAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankAccountService {
    public Mono<BankAccount> createBankAccount(BankAccount bankAccount);
    public Flux<BankAccount> getAllBankAccounts();
    public Mono<BankAccount> getBankAccountById(String id);
    public Mono<BankAccount> updateBankAccount(String id, BankAccount bankAccount);
    public Mono<Void> deleteBankAccount(String id);
}
