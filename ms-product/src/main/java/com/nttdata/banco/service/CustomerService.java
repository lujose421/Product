package com.nttdata.banco.service;

import com.nttdata.banco.persistence.entity.BankAccount;
import com.nttdata.banco.persistence.entity.Credit;
import com.nttdata.product.openapi.model.BankAccountDTO;
import com.nttdata.product.openapi.model.CreditDTO;
import com.nttdata.product.openapi.model.CustomerDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerService {
    public Mono<Boolean> customerExist(String ownerId);
    public Mono<CustomerDTO> customerFindByOwnerId(String ownerId);

    public Mono<BankAccountDTO> createBankAccount(BankAccountDTO bankAccount);
    public Flux<BankAccountDTO> getAllBankAccounts();
    public Mono<CreditDTO> createCredit(CreditDTO creditDTO);
    public Flux<CreditDTO> getAllCredits();
}
