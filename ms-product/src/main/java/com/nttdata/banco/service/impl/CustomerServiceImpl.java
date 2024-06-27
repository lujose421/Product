package com.nttdata.banco.service.impl;

import com.nttdata.banco.openapi.model.BankAccount;
import com.nttdata.banco.openapi.model.Credit;
import com.nttdata.banco.service.CustomerService;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public Mono<BankAccount> createBankAccount(BankAccount bankAccount) {

        return null;
    }

    @Override
    public Flux<BankAccount> getAllBankAccounts() {
        return null;
    }

    @Override
    public Mono<Credit> createCredit(Credit credit) {
        return null;
    }

    @Override
    public Flux<Credit> getAllCredits() {
        return null;
    }

   /* public Mono<BankAccount> createBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public Flux<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public Mono<Credit> createCredit(Credit credit) {
        return creditRepository.save(credit);
    }

    public Flux<Credit> getAllCredits() {
        return creditRepository.findAll();
    }*/
}
