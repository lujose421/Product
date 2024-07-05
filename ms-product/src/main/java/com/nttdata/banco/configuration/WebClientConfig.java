package com.nttdata.banco.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${webclient.base-url-customer}")
    private String baseUrlCustomer;

    @Bean
    public WebClient webClientCustomer() {
        return WebClient.builder()
                .baseUrl(baseUrlCustomer)
                .build();
    }
}
