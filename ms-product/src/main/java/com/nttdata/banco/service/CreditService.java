package com.nttdata.banco.service;

import com.nttdata.product.openapi.model.CreditDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {
    public Flux<CreditDTO> getAllCredits();

    public Mono<CreditDTO> getCreditById(String id);

    public Mono<CreditDTO> createCredit(CreditDTO creditDTO);

    public Mono<CreditDTO> updateCredit(String creditId, CreditDTO creditDTO);

    public Mono<Void> deleteCredit(String id);
}
