package com.nttdata.banco.service;

import com.nttdata.banco.openapi.model.BankAccount;
import com.nttdata.banco.openapi.model.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {

    public Mono<BankAccount> createBankAccount(BankAccount bankAccount);


    public Flux<BankAccount> getAllBankAccounts();

    public Mono<Credit> createCredit(Credit credit);

    public Flux<Credit> getAllCredits();
}
