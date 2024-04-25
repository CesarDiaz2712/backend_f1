package com.cesardiaz.backend.f1.backendf1.models;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.NoArgsConstructor;

@Entity
@NamedQuery(name = "ContractDriver.findAll", query = "SELECT cd FROM ContractDriver cd")
@Table(name = "contract_driver")
@NoArgsConstructor
public class ContractDriver {
    
    @EmbeddedId
    private ContractDriverKey contractDriverKey;

    @ManyToOne
    @MapsId("driverId")
    @JoinColumn(name = "race_driver_id")
    DriverFormulaOne driverFormula;
    
    @ManyToOne
    @MapsId("teamId")
    @JoinColumn(name = "team_id")
    TeamFormulaOne team;

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

    @Column(name= "status")
    private Boolean status;

    

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
        this.status=isActivated;
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
            this.status=true;
    }

    public static ContractDriver instance(DriverFormulaOne driverFormula, TeamFormulaOne team, Date initialContractDate,
    Date finalContractDate, Date dateCreated, Date dateUpdated){
        return new ContractDriver(driverFormula, team, initialContractDate, finalContractDate, dateCreated, dateUpdated);
    }

    public static ContractDriver instance(DriverFormulaOne driverFormula, TeamFormulaOne team, Date initialContractDate,
    Date finalContractDate, Date dateCreated, Date dateUpdated, boolean isActivated){
        return new ContractDriver(driverFormula, team, initialContractDate, finalContractDate, dateCreated, dateUpdated, isActivated);
    }

    public DriverFormulaOne getDriverFormula() {
        return driverFormula;
    }

    public void setDriverFormula(DriverFormulaOne driverFormula) {
        this.driverFormula = driverFormula;
    }

    public TeamFormulaOne getTeam() {
        return team;
    }

    public void setTeam(TeamFormulaOne team) {
        this.team = team;
    }

    public Date getInitialContractDate() {
        return initialContractDate;
    }

    public void setInitialContractDate(Date initialContractDate) {
        this.initialContractDate = initialContractDate;
    }

    public Date getFinalContractDate() {
        return finalContractDate;
    }

    public void setFinalContractDate(Date finalContractDate) {
        this.finalContractDate = finalContractDate;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date datecreated) {
        this.dateCreated = datecreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    
    
}
