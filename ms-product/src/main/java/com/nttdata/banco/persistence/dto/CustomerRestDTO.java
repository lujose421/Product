package com.nttdata.banco.persistence.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerRestDTO {
    private String id;
    private String name;
    private String lastName;
    private String razon;
    private String documentType;
    private String documentNumber;
    private String clientType;
}
