package com.nttdata.banco.service.impl;

import com.nttdata.banco.Mapper.BankAccountMapper;
import com.nttdata.banco.exception.types.NotFoundException;
import com.nttdata.banco.persistence.entity.BankAccount;
import com.nttdata.banco.persistence.repository.BankAccountRepository;
import com.nttdata.banco.service.BankAccountService;
import com.nttdata.product.openapi.model.BankAccountDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private static final String CUSTOMER_NOT_FOUND_MESSAGE = "Customer with ID %s does not exist";

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private CustomerRestServiceImpl customerRestService;

    private static final Logger logger = LoggerFactory.getLogger(BankAccountServiceImpl.class);

    @Override
    public Flux<BankAccountDTO> getAllBankAccounts() {
        logger.info("service getAllBankAccounts - ini");
        Flux<BankAccountDTO> bankAccountsFlux = this.bankAccountRepository.findAll()
                .doOnError(error -> logger.error("Error getAllBankAccounts: ", error))
                .map(BankAccountMapper::entityToDto);
        bankAccountsFlux.count()
                .doOnNext(count -> logger.info("Count bankAccounts: {}", count))
                .subscribe();
        return bankAccountsFlux
                .doOnTerminate(() -> logger.info("service getAllBankAccounts - end"));
    }

    @Override
    public Mono<BankAccountDTO> getBankAccountById(String bankAccountId) {
        logger.info("service getBankAccountById - ini");
        return this.bankAccountRepository.findById(bankAccountId)
                .doOnError(error -> logger.error("Error getBankAccountById: ", error))
                .map(BankAccountMapper::entityToDto)
                .doOnTerminate(() -> logger.info("service getBankAccountById - end"));
    }

    @Override
    public Mono<BankAccountDTO> createBankAccount(BankAccountDTO bankAccountDTO) {
        logger.info("service createBankAccount - ini");
        return this.customerRestService.getCustomerById(bankAccountDTO.getOwnerId())
                .flatMap(customer -> {
                    // Verify ownerId equal in Customer (ms-customer)
                    if (!customer.getId().equals(bankAccountDTO.getOwnerId())) {
                        Mono.error(new NotFoundException(CUSTOMER_NOT_FOUND_MESSAGE, bankAccountDTO.getOwnerId()));
                        return Mono.error(new NotFoundException("OwnerId no exist"));
                    }
                    if ("STAFF".equalsIgnoreCase(customer.getClientType())) {
                        return this.bankAccountRepository.findByOwnerId(bankAccountDTO.getOwnerId())
                                .collectList()
                                .flatMap(existingAccount -> {
                                    Boolean hasSavingAccount = existingAccount.stream()
                                            .anyMatch(acc -> "savings".equalsIgnoreCase(acc.getType().toString()));
                                    Boolean hasCurrentAccount = existingAccount.stream()
                                            .anyMatch(acc -> "current".equalsIgnoreCase(acc.getType().toString()));
                                    Boolean hasFixedTermAccount = existingAccount.stream()
                                            .anyMatch(acc -> "fixed_deposit".equalsIgnoreCase(acc.getType().toString()));


                                    if ("savings".equalsIgnoreCase(bankAccountDTO.getType().toString()) && hasSavingAccount ||
                                            ("current".equalsIgnoreCase(bankAccountDTO.getType().toString()) || hasCurrentAccount) ||
                                            ("fixed_deposit".equalsIgnoreCase(bankAccountDTO.getType().toString()) && hasFixedTermAccount)) {
                                        return Mono.error(new RuntimeException("Personal customer "));
                                    }
                                    return this.saveBankAccount(bankAccountDTO);
                                });
                    } else if ("busines".equalsIgnoreCase(customer.getClientType())) {
                        if ("savings".equalsIgnoreCase(bankAccountDTO.getType().toString()) || "fixed_deposit".equalsIgnoreCase(bankAccountDTO.getType().toString())) {
                            return Mono.error(new RuntimeException("Cliente de tipo Dussines no puede tener una cuenta de tipo ahorro o de tipo plazo fijo"));
                        }
                        return this.saveBankAccount(bankAccountDTO);


                    } else {
                        return Mono.error(new RuntimeException("Tipo de cliente desconocido"));
                    }
                }).doOnTerminate(() -> logger.info("service createBankAccount - end"));
    }

    private Mono<BankAccountDTO> saveBankAccount(BankAccountDTO bankAccountDTO) {
        return Mono.just(bankAccountDTO)
                .map(BankAccountMapper::dtoToEntity)
                .flatMap(this.bankAccountRepository::insert)
                .map(BankAccountMapper::entityToDto)
                .doOnNext(bankAccount -> logger.info("bankAccount create: {}", bankAccount));
    }

    @Override
    public Mono<BankAccountDTO> updateBankAccount(String bankAccountId, BankAccountDTO bankAccountDTO) {
        logger.info("service updateBankAccount - ini");
        return this.bankAccountRepository.findById(bankAccountId)
                .doOnError(error -> logger.info("Error updateBankAccount: ", error))
                .flatMap(existingAccount -> {
                    BankAccount updateBankAccount = BankAccountMapper.dtoToEntity(bankAccountDTO);
                    updateBankAccount.setId(existingAccount.getId());
                    logger.info("customer by id: {}, body customer: {}", existingAccount.getId(), updateBankAccount);
                    return this.bankAccountRepository.save(updateBankAccount);
                })
                .map(BankAccountMapper::entityToDto)
                .doOnTerminate(() -> logger.info("service updateBankAccount - end"));
    }

    @Override
    public Mono<Void> deleteBankAccount(String bankAccountId) {
        return bankAccountRepository.deleteById(bankAccountId);
    }

    @Override
    public Mono<Double> getBalance(String id) {
        return null;
    }
}
