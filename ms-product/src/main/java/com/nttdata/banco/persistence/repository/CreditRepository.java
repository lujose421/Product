package com.nttdata.banco.persistence.repository;

import com.nttdata.banco.persistence.entity.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CreditRepository extends ReactiveMongoRepository<Credit, String> {
    Mono<Boolean> findByOwnerIdAndType(String ownnerId, String type);
    Mono<Double> findBalanceById(String id);
}
