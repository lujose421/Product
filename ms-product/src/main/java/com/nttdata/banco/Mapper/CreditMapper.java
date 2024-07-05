package com.nttdata.banco.Mapper;

import com.nttdata.banco.persistence.entity.Credit;
import com.nttdata.product.openapi.model.CreditDTO;

public class CreditMapper {
    public static CreditDTO entityToDto(Credit credit) {
        if (credit == null) return null;
        CreditDTO newcreditDTO = new CreditDTO();
        newcreditDTO.setId(credit.getId());
        newcreditDTO.setType(credit.getType());
        newcreditDTO.setLimit(credit.getLimit());
        newcreditDTO.setBalance(credit.getBalance());
        newcreditDTO.setOwnerId(credit.getOwnerId());
        //implementar un metodo en especial para mandar a traer la lista de transacciones
        //creditDTO.setTransactions();
        return newcreditDTO;
    }

    public static Credit dtoToEntity(CreditDTO creditDTO) {
        if (creditDTO == null) return null;
        Credit newCredit = new Credit();
        if (creditDTO.getId() != null) {
            newCredit.setId(creditDTO.getId());
        }
        newCredit.setType(creditDTO.getType());
        newCredit.setLimit(creditDTO.getLimit());
        newCredit.setBalance(creditDTO.getBalance());
        newCredit.setDebt(creditDTO.getDebt());
        newCredit.setOwnerId(creditDTO.getOwnerId());
        return newCredit;
    }
}