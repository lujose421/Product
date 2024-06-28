package com.nttdata.banco.persistence.entity;

import com.nttdata.product.openapi.model.BankAccountDTO;
import com.nttdata.product.openapi.model.TransactionDTO;

import java.util.List;

public class Credit {
    private String id;
    private BankAccountDTO.TypeEnum type;
    private Double balance;
    private String ownerId;
    private List<TransactionDTO> transactions;
}
