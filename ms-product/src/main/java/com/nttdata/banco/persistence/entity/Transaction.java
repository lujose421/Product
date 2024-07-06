package com.nttdata.banco.persistence.entity;

import com.nttdata.product.openapi.model.TransactionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Document(collection = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private String id;
    private LocalDate date;
    private Double amount;
    private TransactionDTO.TypeEnum type;
    private TransactionDTO.AccountTypeEnum accountType;
    private String productNum;
    private String description;
}
