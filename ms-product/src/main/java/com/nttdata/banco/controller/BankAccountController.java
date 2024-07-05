package com.nttdata.banco.controller;

import com.nttdata.banco.service.BankAccountService;
import com.nttdata.product.openapi.api.BanckAccountApi;
import com.nttdata.product.openapi.model.BankAccountDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
public class BankAccountController implements BanckAccountApi {

    @Autowired
    private BankAccountService bankAccountService;

    private static final Logger logger = LoggerFactory.getLogger(BankAccountController.class);

    @Override
    public Mono<ResponseEntity<Flux<BankAccountDTO>>> getAllBankAccounts(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok().body(this.bankAccountService.getAllBankAccounts()));
    }

    @Override
    public Mono<ResponseEntity<BankAccountDTO>> getBankAccountById(
            @Valid @PathVariable String bankAccountId, ServerWebExchange exchange) {
        return this.bankAccountService.getBankAccountById(bankAccountId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public Mono<ResponseEntity<BankAccountDTO>> createBankAccount(
            @Valid @RequestBody Mono<BankAccountDTO> bankAccount, ServerWebExchange exchange) {
        return bankAccount
                .flatMap(this.bankAccountService::createBankAccount)
                .map(bankAcct -> ResponseEntity.status(HttpStatus.CREATED).body(bankAcct))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @Override
    public Mono<ResponseEntity<BankAccountDTO>> updateBankAccount(
            @Valid @PathVariable String bankAccountId,
            @Valid @RequestBody Mono<BankAccountDTO> bankAccount, ServerWebExchange exchange) {
        return bankAccount
                .flatMap(bankAcct -> this.bankAccountService.updateBankAccount(bankAccountId, bankAcct))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public Mono<ResponseEntity<Void>> deleteBankAccount(
            @Valid @PathVariable String bankAccountId, ServerWebExchange exchange) {
        return this.bankAccountService.deleteBankAccount(bankAccountId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
