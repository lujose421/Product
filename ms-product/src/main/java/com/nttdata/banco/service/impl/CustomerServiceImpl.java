package com.nttdata.banco.service.impl;

import com.nttdata.banco.service.CustomerService;
import com.nttdata.product.openapi.model.BankAccountDTO;
import com.nttdata.product.openapi.model.CreditDTO;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Override
    public Flux<CreditDTO> getAllCredits() {
        return null;
    }

    @Override
    public Flux<BankAccountDTO> getAllBankAccounts() {
        return null;
    }

    @Override
    public Mono<BankAccountDTO> createBankAccount(BankAccountDTO bankAccountDTO) {

        return null;
    }

    @Override
    public Mono<CreditDTO> createCredit(CreditDTO creditDTO) {
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
