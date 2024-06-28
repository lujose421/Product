package com.nttdata.banco.service.impl;

import com.nttdata.banco.persistence.dto.CustomerRestDTO;
import com.nttdata.banco.service.CustomerRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerRestServiceImpl implements CustomerRestService {

    private final WebClient webClient;

    @Autowired
    public CustomerRestServiceImpl(WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    public Flux<CustomerRestDTO> getAllCustomer() {
        return webClient.get()
                .uri("/api/v1/customer")
                .retrieve()
                .bodyToFlux(CustomerRestDTO.class);
    }

    @Override
    public Mono<CustomerRestDTO> getCustomerById(String id) {
        return null;
    }
}
