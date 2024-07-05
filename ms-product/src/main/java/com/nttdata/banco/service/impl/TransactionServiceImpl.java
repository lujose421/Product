package com.nttdata.banco.service.impl;

import com.nttdata.banco.Constanst.AppContanst;
import com.nttdata.banco.Mapper.TransactionMapper;
import com.nttdata.banco.persistence.entity.Transaction;
import com.nttdata.banco.persistence.repository.TransactionRepository;
import com.nttdata.banco.service.CreditService;
import com.nttdata.banco.service.TransactionService;
import com.nttdata.product.openapi.model.TransactionDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private CreditService creditService;
    private static final Logger logger = LoggerFactory.getLogger(BankAccountServiceImpl.class);


    @Override
    public Mono<TransactionDTO> createTransaction(TransactionDTO transactionDTO) {
        logger.info("service createTransaction - ini");
        return this.validateTransactionProduct(transactionDTO)
                .flatMap(valid -> {
                    if (!AppContanst.BANK_ACCOUNT.equals(valid) || !AppContanst.CREDIT.equals(valid)) {
                        return Mono.error(new RuntimeException("invalid Transaction"));
                    } else if (AppContanst.BANK_ACCOUNT.equals(valid)) {
                        return null;
                        //movimientos que toca hacer con bankaccount
                    } else if (AppContanst.CREDIT.equals(valid)) {
                        return processCreditTransaction(transactionDTO);
                    } else {
                        return Mono.error(new RuntimeException("Unknown account type validation result"));
                    }
                })
                .doOnTerminate(() -> logger.info("service createTransaction - end"));

    }

    private Mono<String> validateTransactionProduct(TransactionDTO transactionDTO) {
        TransactionDTO.AccountTypeEnum accountType = transactionDTO.getAccountType();
        switch (accountType) {
            case SAVINGS:
            case CURRENT:
            case FIXED_DEPOSIT:
                return Mono.just(AppContanst.BANK_ACCOUNT);
            case PERSONAL:
            case BUSINESS:
            case CREDIT_CARD:
                return Mono.just(AppContanst.CREDIT);
            default:
                logger.error("Tipo de  cuenta no encontrada");
                return Mono.error(new RuntimeException("Error: Tipo de cuenta no encontrado"));
        }
    }

    private Mono<Long> validateMonthlyTransactionsOfCredits(String productNum) {
        LocalDate startOfMonth = LocalDate.now().with(TemporalAdjusters.firstDayOfMonth());
        LocalDate ensOfMonth = LocalDate.now().with(TemporalAdjusters.lastDayOfMonth());
        return this.transactionRepository.countAllByProductNumAndDateBetween(productNum, startOfMonth, ensOfMonth);
    }

    private Mono<Boolean> validateBalance(TransactionDTO transactionDTO) {
        if (transactionDTO.getType() == TransactionDTO.TypeEnum.WITHDRAWAL) {
            return creditService.getBalance(transactionDTO.getProductNum())
                    .flatMap(balance -> {
                        if (balance < transactionDTO.getAmount()) {
                            return Mono.error(new RuntimeException("Saldo insuficiente"));
                        }
                        return Mono.just(Boolean.TRUE);
                    });
        }
        return Mono.just(Boolean.TRUE);
    }

    private Mono<Object> updateBalance(TransactionDTO transactionDTO) {
        return creditService.getBalance(transactionDTO.getProductNum())
                .flatMap(currentBalance -> {
                    double newBalance = currentBalance + (transactionDTO.getType() == TransactionDTO.TypeEnum.DEPOSIT ? transactionDTO.getAmount() : -transactionDTO.getAmount());
                    return creditService.getCreditById(transactionDTO.getProductNum())
                            .flatMap(creditDTO -> {
                                creditDTO.setBalance(newBalance);
                                return creditService.updateCredit(creditDTO.getId(), creditDTO);
                            });
                });
    }

    private Mono<TransactionDTO> processCreditTransaction(TransactionDTO transactionDTO) {
        return creditService.getCreditById(transactionDTO.getProductNum())
                .switchIfEmpty(Mono.error(new RuntimeException("Credit Not found")))
                .flatMap(credit -> validateMonthlyTransactionsOfCredits(transactionDTO.getProductNum()))
                .flatMap(credit -> validateBalance(transactionDTO))
                .flatMap(credit -> updateBalance(transactionDTO))
                .flatMap(credit -> {
                    Transaction transaction = TransactionMapper.dtoToEntity(transactionDTO);
                    return transactionRepository.save(transaction)
                            .map(TransactionMapper::entityToDto);
                });

    }

    @Override
    public Mono<TransactionDTO> getTransactionFindByNumOperacion(String numOperacion) {
        logger.info("service getTransaction - ini");
        return this.transactionRepository.findById(numOperacion)
                .doOnError(error -> logger.error("Error getTransaction: ", error))
                .map(TransactionMapper::entityToDto)
                .doOnTerminate(() -> logger.info("service getTransaction - end"));
    }
}
