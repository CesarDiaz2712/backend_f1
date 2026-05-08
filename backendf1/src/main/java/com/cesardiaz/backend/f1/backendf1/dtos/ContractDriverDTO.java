package com.cesardiaz.backend.f1.backendf1.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ContractDriverDTO {

    private Long driverId;
    private Long teamId;
    private String initialContractDate;
    private String finalContractDate;
    private boolean isActive;
    
}
