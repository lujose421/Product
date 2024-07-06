package com.nttdata.banco.persistence.entity;

import com.nttdata.product.openapi.model.BankAccountDTO;
import com.nttdata.product.openapi.model.CreditDTO;
import com.nttdata.product.openapi.model.TransactionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document(collection = "credit")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Credit {
    private String id;
    private CreditDTO.TypeEnum type;
    private Double balance;
    private Double debt;
    private Double limit;
    private String ownerId;
    //private List<TransactionDTO> transactions;
}
