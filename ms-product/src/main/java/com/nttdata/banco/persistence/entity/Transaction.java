package com.nttdata.banco.persistence.entity;

import com.nttdata.product.openapi.model.TransactionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "transaction")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    private String id;
    private String date;
    private Double amount;
    private TransactionDTO.TypeEnum type;
    private String description;
}
