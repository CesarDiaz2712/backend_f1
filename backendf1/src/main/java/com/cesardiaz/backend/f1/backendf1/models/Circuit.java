package com.cesardiaz.backend.f1.backendf1.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@NamedQuery(name = "Circuit.findAll", query = "SELECT c FROM Circuit c")
@Table(name = "circuit")
public class Circuit extends AbstractPersistableCustom<Long>{

    @Column(name = "name", nullable = false, length = 45)
    private String name;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cv_country_id", nullable = false, foreignKey = @ForeignKey(name = "fk_circuit_cv_country_id"))
    private CodeValue codeValueCountry;

    @Column(name= "date_created")
    private LocalDate datecreated;
    
    @Column(name= "date_updated")
    private LocalDate dateUpdated;


    
}
