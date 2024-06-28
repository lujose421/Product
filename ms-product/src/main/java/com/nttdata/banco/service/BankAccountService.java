package com.nttdata.banco.service;

import com.nttdata.banco.persistence.entity.BankAccount;
import com.nttdata.product.openapi.model.BankAccountDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface BankAccountService {
    public Flux<BankAccountDTO> getAllBankAccounts();
    public Mono<BankAccountDTO> createBankAccount(BankAccountDTO bankAccountDTO);
    public Mono<BankAccountDTO> getBankAccountById(String bankAccountId);
    public Mono<BankAccountDTO> updateBankAccount(String bankAccountId, BankAccountDTO bankAccountDTO);
    public Mono<Void> deleteBankAccount(String bankAccountId);
}
