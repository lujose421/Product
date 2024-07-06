package com.nttdata.banco.service.impl;

import com.nttdata.banco.Constanst.AppContanst;
import com.nttdata.banco.persistence.dto.CustomerRestDTO;
import com.nttdata.banco.service.CustomerRestService;
import com.nttdata.product.openapi.model.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
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
        return webClient.get()
                .uri("/api/v1/customer/{id}", id)
                .retrieve()
                .bodyToMono(CustomerRestDTO.class)
                .onErrorResume(WebClientResponseException.class, Mono::error);
    }

    @Override
    public Mono<CustomerDTO> customerFindByOwnerId(String ownerId) {
        return webClient.get()
                .uri(AppContanst.OBTENER_CUSTOMER_ID + ownerId)
                .retrieve()
                .bodyToMono(CustomerDTO.class)
                .onErrorResume(WebClientResponseException.NotFound.class, e -> Mono.empty())
                .onErrorResume(WebClientResponseException.class, e -> {
                    return Mono.error(new RuntimeException("Error fetching customer", e));
                });
    }

    @Override
    public Mono<Boolean> customerExist(String ownerId) {
        return webClient
                .get()
                .uri(AppContanst.OBTENER_CUSTOMER_ID + ownerId)
                .retrieve()
                .toBodilessEntity()
                .map(response -> Boolean.TRUE)
                .onErrorResume(WebClientResponseException.NotFound.class, e -> Mono.just(Boolean.FALSE))
                .onErrorResume(WebClientResponseException.class, e -> {
                    return Mono.error(new RuntimeException("Error al comprobar Existencia Del cliente", e));
                });
    }
}
