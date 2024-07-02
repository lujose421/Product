package com.nttdata.banco.persistence.repository;

import com.nttdata.banco.persistence.entity.Transaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends ReactiveMongoRepository<Transaction,String> {
}
