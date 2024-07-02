package com.nttdata.banco.service;

import com.nttdata.banco.persistence.dto.CustomerRestDTO;
import org.springframework.http.ResponseEntity;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface CustomerRestService {
    Flux<CustomerRestDTO> getAllCustomer();
    Mono<CustomerRestDTO> getCustomerById(String id);
}
