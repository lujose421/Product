package com.nttdata.banco.service.impl;

import com.nttdata.banco.openapi.model.Credit;
import com.nttdata.banco.persistence.repository.CreditRepository;
import com.nttdata.banco.service.CreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements CreditService {
    @Autowired
    private CreditRepository creditRepository;

    @Override
    public Mono<Credit> createCredit(Credit credit) {
        return creditRepository.save(credit);
    }

    @Override
    public Flux<Credit> getAllCredits() {
        return creditRepository.findAll();
    }

    @Override
    public Mono<Credit> getCreditById(String id) {
        return creditRepository.findById(id);
    }

    @Override
    public Mono<Credit> updateCredit(String id, Credit credit) {
        return creditRepository.findById(id)
                .flatMap(existingCredit -> {
                    existingCredit.setType(credit.getType());
                    existingCredit.setLimit(credit.getLimit());
                    existingCredit.setBalance(credit.getBalance());
                    existingCredit.setOwnerId(credit.getOwnerId());
                    existingCredit.setTransactions(credit.getTransactions());
                    return creditRepository.save(existingCredit);
                });
    }

    @Override
    public Mono<Void> deleteCredit(String id) {
        return creditRepository.deleteById(id);
    }
}
