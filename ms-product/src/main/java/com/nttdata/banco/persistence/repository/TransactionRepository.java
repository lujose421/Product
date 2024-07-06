package com.nttdata.banco.persistence.repository;

import com.nttdata.banco.persistence.entity.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction, String> {
    Mono<Long> countAllByProductNumAndDateBetween(String productNum, LocalDate startDate, LocalDate endDate);
}
