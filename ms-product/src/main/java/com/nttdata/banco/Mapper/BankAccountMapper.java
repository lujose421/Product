package com.nttdata.banco.Mapper;

import com.nttdata.banco.persistence.entity.BankAccount;
import com.nttdata.product.openapi.model.BankAccountDTO;

public class BankAccountMapper {
    public static BankAccountDTO entityToDto(BankAccount bankAccount) {
        BankAccountDTO newBankAccountDTO = new BankAccountDTO();
        newBankAccountDTO.setId(bankAccount.getId());
        newBankAccountDTO.setType(bankAccount.getType());
        newBankAccountDTO.setOwnerId(bankAccount.getOwnerId());
        newBankAccountDTO.setBalance(bankAccount.getBalance());
        newBankAccountDTO.setTransactionLimit(bankAccount.getTransactionLimit());
        newBankAccountDTO.setHolders(bankAccount.getHolders());
     //   newBankAccountDTO.setAuthorizedSigners(bankAccount.getAuthorizedSigners());
        return newBankAccountDTO;
    }

    public static BankAccount dtoToEntity(BankAccountDTO bankAccountDTO) {
        BankAccount newBankAccount = new BankAccount();
        newBankAccount.setId(bankAccountDTO.getId());
        newBankAccount.setType(bankAccountDTO.getType());
        newBankAccount.setOwnerId(bankAccountDTO.getOwnerId());
        newBankAccount.setBalance(bankAccountDTO.getBalance());
        newBankAccount.setTransactionLimit(bankAccountDTO.getTransactionLimit());
        newBankAccount.setHolders(bankAccountDTO.getHolders());
      //  newBankAccount.setAuthorizedSigners(bankAccountDTO.getAuthorizedSigners());
        return newBankAccount;
    }
}
