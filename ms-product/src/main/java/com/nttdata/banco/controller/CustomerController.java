package com.nttdata.banco.controller;

import com.nttdata.banco.persistence.dto.CustomerRestDTO;
import com.nttdata.banco.service.CustomerRestService;
import com.nttdata.product.openapi.model.CustomerDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRestService customerRestService;

    @GetMapping
    public Flux<CustomerRestDTO> getAll() {
        return this.customerRestService.getAllCustomer();
    }

}
