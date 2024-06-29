package com.nttdata.banco.config;

import com.nttdata.banco.Constanst.AppContanst;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.reactive.function.client.WebClient;

public class CustomerWebClient {
    @Value("${microservice.customerUrl}")
    private String customerUrl;

    void getCustomerByWebClient(String ownerId) {
        WebClient webClient = WebClient.builder().baseUrl(customerUrl).build();
    }
}
