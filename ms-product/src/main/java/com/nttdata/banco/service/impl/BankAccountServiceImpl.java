package com.nttdata.banco.service.impl;

import com.nttdata.banco.openapi.model.BankAccount;
import com.nttdata.banco.persistence.repository.BankAccountRepository;
import com.nttdata.banco.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Override
    public Mono<BankAccount> createBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    @Override
    public Flux<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    @Override
    public Mono<BankAccount> getBankAccountById(String id) {
        return bankAccountRepository.findById(id);
    }

    @Override
    public Mono<BankAccount> updateBankAccount(String id, BankAccount bankAccount) {
        return bankAccountRepository.findById(id)
                .flatMap(existingAccount -> {
                    existingAccount.setType(bankAccount.getType());
                    existingAccount.setBalance(bankAccount.getBalance());
                    existingAccount.setTransactionLimit(bankAccount.getTransactionLimit());
                    existingAccount.setHolders(bankAccount.getHolders());
                    existingAccount.setAuthorizedSigners(bankAccount.getAuthorizedSigners());
                    existingAccount.setTransactions(bankAccount.getTransactions());
                    return bankAccountRepository.save(existingAccount);
                });
    }

    @Override
    public Mono<Void> deleteBankAccount(String id) {
        return bankAccountRepository.deleteById(id);
    }
}
