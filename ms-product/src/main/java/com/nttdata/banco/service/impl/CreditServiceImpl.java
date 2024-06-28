package com.nttdata.banco.service.impl;

import com.nttdata.banco.persistence.repository.CreditRepository;
import com.nttdata.banco.service.CreditService;
import com.nttdata.product.openapi.model.CreditDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CreditServiceImpl implements CreditService {
    @Autowired
    private CreditRepository creditRepository;

    @Override
    public Mono<CreditDTO> createCredit(CreditDTO creditDTO) {
//        return creditRepository.save(creditDTO);
        return Mono.empty();
    }

    @Override
    public Flux<CreditDTO> getAllCredits() {
//        return creditRepository.findAll();
        return Flux.empty();
    }

    @Override
    public Mono<CreditDTO> getCreditById(String id) {
//        return creditRepository.findById(id);
        return Mono.empty();
    }

    @Override
    public Mono<CreditDTO> updateCredit(String id, CreditDTO creditDTO) {
//        return creditRepository.findById(id)
//                .flatMap(existingCredit -> {
//                    existingCredit.setType(creditDTO.getType());
//                    existingCredit.setLimit(creditDTO.getLimit());
//                    existingCredit.setBalance(creditDTO.getBalance());
//                    existingCredit.setOwnerId(creditDTO.getOwnerId());
//                    existingCredit.setTransactions(creditDTO.getTransactions());
//                    return creditRepository.save(existingCredit);
//                });
        return Mono.empty();
    }

    @Override
    public Mono<Void> deleteCredit(String id) {
        return creditRepository.deleteById(id);
    }
}
