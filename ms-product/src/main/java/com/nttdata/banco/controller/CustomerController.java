package com.nttdata.banco.controller;

import com.nttdata.banco.persistence.dto.CustomerRestDTO;
import com.nttdata.banco.service.CustomerRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private CustomerRestService customerRestService;

    @GetMapping
    public Flux<CustomerRestDTO> getAll() {
        return this.customerRestService.getAllCustomer();
    }

    @GetMapping("/{id}")
    public Mono<CustomerRestDTO> getById(@PathVariable(value = "id") String id) {
        return this.customerRestService.getCustomerById(id);
    }

}
