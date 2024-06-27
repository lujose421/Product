package com.nttdata.banco.persistence.repository;

import com.nttdata.banco.openapi.model.Customer;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends ReactiveMongoRepository<Customer,String> {

}
