package com.nttdata.banco.Mapper;

import com.nttdata.banco.persistence.entity.Transaction;
import com.nttdata.product.openapi.model.TransactionDTO;

public class TransactionMapper {

    public static TransactionDTO entityToDto(Transaction transaction) {
        if (transaction == null) return null;
        TransactionDTO newTransactionDTO = new TransactionDTO();
        newTransactionDTO.setId(transaction.getId());
        newTransactionDTO.setType(transaction.getType());
        newTransactionDTO.setAmount(transaction.getAmount());
        newTransactionDTO.setDate(transaction.getDate());
        newTransactionDTO.setAccountType(transaction.getAccountType());
        newTransactionDTO.setProductNum(transaction.getProductNum());
        newTransactionDTO.setDescription(transaction.getDescription());
        return newTransactionDTO;
    }

    public static Transaction dtoToEntity(TransactionDTO transactionDTO) {
        if (transactionDTO == null) return null;
        Transaction newTransaction = new Transaction();
        if (transactionDTO.getId() != null) {
            newTransaction.setId(transactionDTO.getId());
        }
        newTransaction.setType(transactionDTO.getType());
        newTransaction.setAmount(transactionDTO.getAmount());
        newTransaction.setDate(transactionDTO.getDate());
        newTransaction.setAccountType(transactionDTO.getAccountType());
        newTransaction.setProductNum(transactionDTO.getProductNum());
        newTransaction.setDescription(transactionDTO.getDescription());
        return newTransaction;
    }
}