package com.cesardiaz.backend.f1.backendf1.models;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class ContractDriverKey {
    
    @Column(name= "driver_id")
    private Long driverId;

    @Column(name= "team_id")
    private Long teamId;

    public ContractDriverKey(Long driverId, Long teamId) {
        this.driverId = driverId;
        this.teamId = teamId;
    }

    public ContractDriverKey() {
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

    
}
