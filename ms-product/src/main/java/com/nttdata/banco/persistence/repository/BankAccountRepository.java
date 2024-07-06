package com.nttdata.banco.persistence.repository;

import com.nttdata.banco.persistence.entity.BankAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface BankAccountRepository extends ReactiveMongoRepository<BankAccount, String> {

    Flux<BankAccount> findByOwnerId(String ownerId);

    Mono<Double> findBalanceById(String ownerId);

}
