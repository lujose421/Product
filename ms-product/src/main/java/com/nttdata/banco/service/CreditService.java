package com.nttdata.banco.service;

import com.nttdata.banco.openapi.model.Credit;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CreditService {
    public Mono<Credit> createCredit(Credit credit);

    public Flux<Credit> getAllCredits();

    public Mono<Credit> getCreditById(String id);

    public Mono<Credit> updateCredit(String id, Credit credit);

    public Mono<Void> deleteCredit(String id);

}
