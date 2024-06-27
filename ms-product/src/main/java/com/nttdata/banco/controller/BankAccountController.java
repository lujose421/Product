package com.nttdata.banco.controller;


import com.nttdata.banco.openapi.api.BanckAccountApi;
import com.nttdata.banco.openapi.model.BankAccount;
import com.nttdata.banco.service.BankAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class BankAccountController implements BanckAccountApi {

    @Autowired
    private BankAccountService bankAccountService;

    @Override
    public Mono<ResponseEntity<BankAccount>> createBankAccount(
            @RequestBody Mono<BankAccount> bankAccount, ServerWebExchange exchange) {
        return bankAccount
                .flatMap(this.bankAccountService::createBankAccount)
                .map(bankAcct -> ResponseEntity.status(HttpStatus.CREATED).body(bankAcct))
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());

    }

    @Override
    public Mono<ResponseEntity<Void>> deleteBankAccount(
            @PathVariable String bankAccountId, ServerWebExchange exchange) {
        return this.bankAccountService.deleteBankAccount(bankAccountId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public Mono<ResponseEntity<Flux<BankAccount>>> getAllBankAccounts(ServerWebExchange exchange) {
        return Mono.just(ResponseEntity.ok().body(this.bankAccountService.getAllBankAccounts()));
    }

    @Override
    public Mono<ResponseEntity<BankAccount>> getBankAccountById(
            @PathVariable String bankAccountId, ServerWebExchange exchange) {
        return this.bankAccountService.getBankAccountById(bankAccountId)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @Override
    public Mono<ResponseEntity<BankAccount>> updateBankAccount(
            @PathVariable String bankAccountId,
            @RequestBody Mono<BankAccount> bankAccount, ServerWebExchange exchange) {
        return bankAccount
                .flatMap(bankacct -> this.bankAccountService.updateBankAccount(bankAccountId, bankacct))
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
