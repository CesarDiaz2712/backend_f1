package com.cesardiaz.backend.f1.backendf1.models;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.ForeignKey;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NamedQuery(name = "ContractDriver.findAll", query = "SELECT cd FROM ContractDriver cd")
@Table(name = "contract_driver")
@NoArgsConstructor
public class ContractDriver {
    
    @EmbeddedId
    private ContractDriverKey contractDriverKey;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("driverId")
    @JoinColumn(name = "driver_id", nullable = false, foreignKey = @ForeignKey(name = "fk_contract_driver_driver_id"))
    private DriverFormulaOne driverFormula;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("teamId")
    @JoinColumn(name = "team_id", nullable = false, foreignKey = @ForeignKey(name = "fk_contract_driver_team_id"))
    private TeamFormulaOne team;

    @Column(name= "date_initial_contract")
    private Date initialContractDate;

    @Column(name= "date_end_contract")
    private Date finalContractDate;
    
    @Column(name= "date_created")
    @Temporal(TemporalType.DATE)
    private Date dateCreated;
    
    @Column(name= "date_updated")
    @Temporal(TemporalType.DATE)
    private Date dateUpdated;

    @Column(name= "is_activated")
    private boolean isActived;

    

    private  ContractDriver(DriverFormulaOne driverFormula, TeamFormulaOne team, Date initialContractDate,
            Date finalContractDate, Date dateCreated, Date dateUpdated, boolean isActivated) {
        this.driverFormula = driverFormula;
        this.team = team;
        this.initialContractDate = initialContractDate;
        this.finalContractDate = finalContractDate;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        contractDriverKey= new ContractDriverKey();
        contractDriverKey.setDriverId(driverFormula.getId());
        contractDriverKey.setTeamId(team.getId());
        this.isActived=isActivated;
    }

    private  ContractDriver(DriverFormulaOne driverFormula, TeamFormulaOne team, Date initialContractDate,
        Date finalContractDate, Date dateCreated, Date dateUpdated) {
            this.driverFormula = driverFormula;
            this.team = team;
            this.initialContractDate = initialContractDate;
            this.finalContractDate = finalContractDate;
            this.dateCreated = dateCreated;
            this.dateUpdated = dateUpdated;
            contractDriverKey= new ContractDriverKey();
            contractDriverKey.setDriverId(driverFormula.getId());
            contractDriverKey.setTeamId(team.getId());
            this.isActived=true;
    }

    public static ContractDriver instance(DriverFormulaOne driverFormula, TeamFormulaOne team, Date initialContractDate,
    Date finalContractDate, Date dateCreated, Date dateUpdated){
        return new ContractDriver(driverFormula, team, initialContractDate, finalContractDate, dateCreated, dateUpdated);
    }

    public static ContractDriver instance(DriverFormulaOne driverFormula, TeamFormulaOne team, Date initialContractDate,
    Date finalContractDate, Date dateCreated, Date dateUpdated, boolean isActivated){
        return new ContractDriver(driverFormula, team, initialContractDate, finalContractDate, dateCreated, dateUpdated, isActivated);
    }
    
    
}
