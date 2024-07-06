package com.nttdata.banco.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String id;
    private String name;
    private String lastName;
    private String razon;
    private String documentType;
    private String documentNumber;
    private String clientType;
}
