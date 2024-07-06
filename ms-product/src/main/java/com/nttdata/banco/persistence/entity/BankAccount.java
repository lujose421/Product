package com.nttdata.banco.persistence.entity;

import com.nttdata.product.openapi.model.AuthorizedSignersDTO;
import com.nttdata.product.openapi.model.BankAccountDTO;
import com.nttdata.product.openapi.model.HoldersDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "account")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankAccount {
    private String id;
    private BankAccountDTO.TypeEnum type;
    private String ownerId;
    private Double balance;
    private Integer transactionLimit;
    private List<HoldersDTO> holders;
    //private List<AuthorizedSignersDTO> authorizedSigners;
}
