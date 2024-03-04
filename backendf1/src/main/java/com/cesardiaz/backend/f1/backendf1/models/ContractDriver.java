package com.cesardiaz.backend.f1.backendf1.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
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

    public ContractDriver(Date initialContractDate, Date finalContractDate, Date datecreated, Date dateUpdated) {
        this.initialContractDate = initialContractDate;
        this.finalContractDate = finalContractDate;
        this.datecreated = datecreated;
        this.dateUpdated = dateUpdated;
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
