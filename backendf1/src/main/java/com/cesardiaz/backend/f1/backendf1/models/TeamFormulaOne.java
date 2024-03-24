package com.cesardiaz.backend.f1.backendf1.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "team")
@NamedQuery(name = "TeamFormulaOne.findAll", query = "SELECT tf FROM TeamFormulaOne tf")
@NoArgsConstructor
public class TeamFormulaOne extends AbstractPersistableCustom<Long>{

    @Column(name = "name", columnDefinition = "varchar(30)", unique = true)
    private String name;

    @Column(name = "alias", columnDefinition = "varchar(5)")
    private String alias;

    @Column(name = "year_register", columnDefinition = "varchar(4)")
    private String yearRegister;

    @Column(name = "teamChief", columnDefinition = "varchar(40)")
    private String teamChief;

    @Column(name = "technical_chief", columnDefinition = "varchar(40)")
    private String technicalChief;

    @Column(name = "dateCreated")
    private Date dateCreated;

    @Column(name = "dateUpdated")
    private Date dateUpdated;

    public TeamFormulaOne(Long id, String name, String alias, String yearRegister, String teamChief,
            String technicalChief, Date dateCreated, Date dateUpdated) {
        setId(id);
        this.name = name;
        this.alias = alias;
        this.yearRegister = yearRegister;
        this.teamChief = teamChief;
        this.technicalChief = technicalChief;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getYearRegister() {
        return yearRegister;
    }

    public void setYearRegister(String yearRegister) {
        this.yearRegister = yearRegister;
    }

    public String getTeamChief() {
        return teamChief;
    }

    public void setTeamChief(String teamChief) {
        this.teamChief = teamChief;
    }

    public String getTechnicalChief() {
        return technicalChief;
    }

    public void setTechnicalChief(String technicalChief) {
        this.technicalChief = technicalChief;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(Date dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    
}
