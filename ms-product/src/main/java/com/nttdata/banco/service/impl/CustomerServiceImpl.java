package com.nttdata.banco.service.impl;

import com.nttdata.banco.Constanst.AppContanst;
import com.nttdata.banco.service.CustomerService;
import com.nttdata.product.openapi.model.BankAccountDTO;
import com.nttdata.product.openapi.model.CreditDTO;
import com.nttdata.product.openapi.model.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerServiceImpl implements CustomerService {


    @Value("${microservice.customerUrl}")
    private String customerUrl;

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Override
    public Flux<CreditDTO> getAllCredits() {
        return null;
    }

    @Override
    public Flux<BankAccountDTO> getAllBankAccounts() {
        return null;
    }

    @Override
    public Mono<Boolean> customerExist(String ownerId) {
        return webClientBuilder.build()
                .get()
                .uri(customerUrl + AppContanst.OBTENER_CUSTOMER_ID + ownerId)
                .retrieve()
                .toBodilessEntity()
                .map(response -> Boolean.TRUE)
                .onErrorResume(WebClientResponseException.NotFound.class, e -> Mono.just(Boolean.FALSE))
                .onErrorResume(WebClientResponseException.class, e -> {
                    return Mono.error(new RuntimeException("Error al comprobar Existencia Del cliente", e));
                });
    }

    @Override
    public Mono<CustomerDTO> customerFindByOwnerId(String ownerId) {
        return webClientBuilder.build()
                .get()
                .uri(customerUrl + AppContanst.OBTENER_CUSTOMER_ID + ownerId)
                .retrieve()
                .bodyToMono(CustomerDTO.class)
                .onErrorResume(WebClientResponseException.NotFound.class, e -> Mono.empty())
                .onErrorResume(WebClientResponseException.class, e -> {
                    return Mono.error(new RuntimeException("Error fetching customer", e));
                });
    }

    @Override
    public Mono<BankAccountDTO> createBankAccount(BankAccountDTO bankAccountDTO) {

        return null;
    }

    @Override
    public Mono<CreditDTO> createCredit(CreditDTO creditDTO) {
        //Mandar a traer el cliente por el owner ID
        //empara el cliente en un DTO


        return null;
    }

   /* public Mono<BankAccount> createBankAccount(BankAccount bankAccount) {
        return bankAccountRepository.save(bankAccount);
    }

    public Flux<BankAccount> getAllBankAccounts() {
        return bankAccountRepository.findAll();
    }

    public Mono<Credit> createCredit(Credit credit) {
        return creditRepository.save(credit);
    }

    public Flux<Credit> getAllCredits() {
        return creditRepository.findAll();
    }*/
}
