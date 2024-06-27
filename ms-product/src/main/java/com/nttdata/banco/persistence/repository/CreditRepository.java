package com.nttdata.banco.persistence.repository;

import com.nttdata.banco.openapi.model.Credit;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditRepository extends ReactiveMongoRepository<Credit, String> {
}
