package com.cesardiaz.backend.f1.backendf1.models;

import java.time.LocalDate;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedQuery(name = "DriverFormulaOne.findAll", query = "SELECT dr FROM DriverFormulaOne dr")
@Table(name = "f1_driver")
public class DriverFormulaOne extends AbstractPersistableCustom<Long>{

    @Column(name= "firstname", columnDefinition="varchar(45)", nullable = false)
    private String firstname;

    @Column(name= "lastname", columnDefinition="varchar(45)", nullable = false)
    private String lastname;
    
    @Column(name= "gamertag", columnDefinition="varchar(45)", unique = true, nullable = false)
    private String gamertag;
    
    @Column(name= "number_driver", columnDefinition="varchar(2)", nullable = false)
    private String numberDriver;
    
    @Column(name= "date_created", nullable = false)
    private LocalDate datecreated;
    
    @Column(name= "date_updated", nullable = true)
    private LocalDate dateUpdated;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, foreignKey = @ForeignKey(name = "fk_f1_driver_user_id"))
    private UserApp user;

    
    @OneToMany(mappedBy = "driverFormula")
    Set<ContractDriver> contracts;

    public DriverFormulaOne(String name, String lastname, String gamertag, String numberDriver, LocalDate datecreated,
        LocalDate dateUpdated, UserApp user) {
        this.firstname = name;
        this.lastname = lastname;
        this.gamertag = gamertag;
        this.numberDriver = numberDriver;
        this.datecreated = datecreated;
        this.dateUpdated = dateUpdated;
        this.user = user;
    }
    
    public DriverFormulaOne(Long id){
        setId(id);
    }

    public DriverFormulaOne(Long id, String name, String lastname, String gamertag, String numberDriver, LocalDate datecreated,
        LocalDate dateUpdated, UserApp user) {
        setId(id);
        this.firstname = name;
        this.lastname = lastname;
        this.gamertag = gamertag;
        this.numberDriver = numberDriver;
        this.datecreated = datecreated;
        this.dateUpdated = dateUpdated;
        this.user = user;
    }

    public static DriverFormulaOne createDriver(Long id, String name, String lastname, String gamertag, String numberDriver, LocalDate datecreated,
    LocalDate dateUpdated, UserApp user){
        return new DriverFormulaOne(id, name, lastname, gamertag, numberDriver, datecreated, dateUpdated, user);
    }

    public static DriverFormulaOne instance(Long id, String name, String lastname, String gamertag, String numberDriver, LocalDate datecreated,
        LocalDate dateUpdated, UserApp user){
            return new DriverFormulaOne(id, name, lastname, gamertag, numberDriver, datecreated, dateUpdated, user);
    }
}
