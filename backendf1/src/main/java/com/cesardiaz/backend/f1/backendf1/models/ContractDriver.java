package com.cesardiaz.backend.f1.backendf1.models;

import java.sql.Date;

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

@Entity
@NamedQuery(name = "ContractDriver.findAll", query = "SELECT cd FROM ContractDriver cd")
@Table(name = "contract_driver")
public class ContractDriver {
    
    @EmbeddedId
    private ContractDriverKey contractDriverKey;

    
    @ManyToOne
    @MapsId("driverId")
    @JoinColumn(name = "driver_id")
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
    private Date datecreated;
    
    @Column(name= "date_updated")
    @Temporal(TemporalType.DATE)
    private Date dateUpdated;

    

    public ContractDriver(ContractDriverKey contractDriverKey,
            Date initialContractDate, Date finalContractDate, Date datecreated, Date dateUpdated) {
        this.contractDriverKey = contractDriverKey;
        this.initialContractDate = initialContractDate;
        this.finalContractDate = finalContractDate;
        this.datecreated = datecreated;
        this.dateUpdated = dateUpdated;
    }

    public ContractDriverKey getContractDriverKey() {
        return contractDriverKey;
    }

    public void setContractDriverKey(ContractDriverKey contractDriverKey) {
        this.contractDriverKey = contractDriverKey;
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

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    
}
