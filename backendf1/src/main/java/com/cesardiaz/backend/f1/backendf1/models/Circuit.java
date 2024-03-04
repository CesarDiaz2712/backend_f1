package com.cesardiaz.backend.f1.backendf1.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@NamedQuery(name = "Circuit.findAll", query = "SELECT c FROM Circuit c")
@Table(name = "circuit")
public class Circuit extends AbstractPersistableCustom<Long>{

    @Column(name = "name", nullable = false)
    private String name;
    
    @ManyToOne
    @JoinColumn(name = "code_value_country", nullable = false)
    private CodeValue codeValueCountry;

    @Column(name= "date_created")
    private LocalDate datecreated;
    
    @Column(name= "date_updated")
    private LocalDate dateUpdated;

    public Circuit(String name, CodeValue codeValueCountry, LocalDate datecreated, LocalDate dateUpdated) {
        this.name = name;
        this.codeValueCountry = codeValueCountry;
        this.datecreated = datecreated;
        this.dateUpdated = dateUpdated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CodeValue getCodeValueCountry() {
        return codeValueCountry;
    }

    public void setCodeValueCountry(CodeValue codeValueCountry) {
        this.codeValueCountry = codeValueCountry;
    }

    public LocalDate getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(LocalDate datecreated) {
        this.datecreated = datecreated;
    }

    public LocalDate getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDate dateUpdated) {
        this.dateUpdated = dateUpdated;
    }


    
}
