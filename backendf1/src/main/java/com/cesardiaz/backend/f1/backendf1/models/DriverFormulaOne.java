package com.cesardiaz.backend.f1.backendf1.models;

import java.sql.Date;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@NamedQuery(name = "DriverFormulaOne.findAll", query = "SELECT dr FROM DriverFormulaOne dr")
@Table(name = "driver_racer")
public class DriverFormulaOne extends AbstractPersistableCustom<Long>{

    @Column(name= "name", columnDefinition="varchar(20)")
    private String name;

    @Column(name= "lastname", columnDefinition="varchar(20)")
    private String lastname;
    
    @Column(name= "gamertag", columnDefinition="varchar(20)", unique = true)
    private String gamertag;
    
    @Column(name= "number_driver", columnDefinition="varchar(2)")
    private String numberDriver;
    
    @Column(name= "date_created")
    @Temporal(TemporalType.DATE)
    private Date datecreated;
    
    @Column(name= "date_updated")
    @Temporal(TemporalType.DATE)
    private Date dateUpdated;
    
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserApp user;

    public DriverFormulaOne(Long id, String name, String lastname, String gamertag, String numberDriver, Date datecreated,
            Date dateUpdated, UserApp user) {
        setId(id);
        this.name = name;
        this.lastname = lastname;
        this.gamertag = gamertag;
        this.numberDriver = numberDriver;
        this.datecreated = datecreated;
        this.dateUpdated = dateUpdated;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGamertag() {
        return gamertag;
    }

    public void setGamertag(String gamertag) {
        this.gamertag = gamertag;
    }

    public String getNumberDriver() {
        return numberDriver;
    }

    public void setNumberDriver(String numberDriver) {
        this.numberDriver = numberDriver;
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

    public UserApp getUser() {
        return user;
    }

    public void setUser(UserApp user) {
        this.user = user;
    }

    
}
