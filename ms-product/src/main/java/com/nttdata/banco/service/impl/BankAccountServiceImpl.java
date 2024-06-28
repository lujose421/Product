package com.nttdata.banco.service.impl;

import com.nttdata.banco.persistence.entity.BankAccount;
import com.nttdata.banco.persistence.repository.BankAccountRepository;
import com.nttdata.banco.service.BankAccountService;
import com.nttdata.banco.utils.AppUtils;
import com.nttdata.product.openapi.model.BankAccountDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private BankAccountRepository bankAccountRepository;

    private static final Logger logger = LoggerFactory.getLogger(BankAccountServiceImpl.class);

    @Override
    public Flux<BankAccountDTO> getAllBankAccounts() {
        logger.info("service getAllBankAccounts - ini");
        Flux<BankAccountDTO> bankAccountsFlux = this.bankAccountRepository.findAll()
                .doOnError(error -> logger.error("Error getAllBankAccounts: ", error))
                .map(AppUtils::entityToDto);
        bankAccountsFlux.count()
                .doOnNext(count -> logger.info("Count bankAccounts: {}", count))
                .subscribe();
        return bankAccountsFlux
                .doOnTerminate(() -> logger.info("service getAllBankAccounts - end"));
    }

    @Override
    public Mono<BankAccountDTO> createBankAccount(BankAccountDTO bankAccountDTO) {
        logger.info("service createBankAccount - ini");
        return Mono.just(bankAccountDTO)
                .map(AppUtils::dtoToEntity)
                .flatMap(this.bankAccountRepository::insert)
                .map(AppUtils::entityToDto)
                .doOnNext(bankAccount -> logger.info("bankAccount create: {}", bankAccount))
                .doOnTerminate(() -> logger.info("service createBankAccount - end"));
    }

    @Override
    public Mono<BankAccountDTO> getBankAccountById(String bankAccountId) {
        logger.info("service getBankAccountById - ini");
        return this.bankAccountRepository.findById(bankAccountId)
                .doOnError(error -> logger.error("Error getBankAccountById: ", error))
                .map(AppUtils::entityToDto)
                .doOnTerminate(() -> logger.info("service getBankAccountById - end"));
    }

    @Override
    public Mono<BankAccountDTO> updateBankAccount(String bankAccountId, BankAccountDTO bankAccountDTO) {
        logger.info("service updateBankAccount - ini");
        return this.bankAccountRepository.findById(bankAccountId)
                .doOnError(error -> logger.info("Error updateBankAccount: ", error))
                .flatMap(existingAccount -> {
                    BankAccount updateBankAccount = AppUtils.dtoToEntity(bankAccountDTO);
                    updateBankAccount.setId(existingAccount.getId());
                    logger.info("customer by id: {}, body customer: {}", existingAccount.getId(), updateBankAccount);
                    return this.bankAccountRepository.save(updateBankAccount);
                })
                .map(AppUtils::entityToDto)
                .doOnTerminate(() -> logger.info("service updateBankAccount - end"));
    }

    @Override
    public Mono<Void> deleteBankAccount(String bankAccountId) {
        return bankAccountRepository.deleteById(bankAccountId);
    }
}
