package com.cesardiaz.backend.f1.backendf1.dtos;

public class ContractDriverDTO {

    private Long driverId;
    private Long teamId;
    private String initialContractDate;
    private String finalContractDate;
    
    public ContractDriverDTO(Long driverId, Long teamId, String initialContractDate, String finalContractDate) {
        this.driverId = driverId;
        this.teamId = teamId;
        this.initialContractDate = initialContractDate;
        this.finalContractDate = finalContractDate;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getInitialContractDate() {
        return initialContractDate;
    }

    public void setInitialContractDate(String initialContractDate) {
        this.initialContractDate = initialContractDate;
    }

    public String getFinalContractDate() {
        return finalContractDate;
    }

    public void setFinalContractDate(String finalContractDate) {
        this.finalContractDate = finalContractDate;
    }

    
}
