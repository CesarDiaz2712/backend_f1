package com.cesardiaz.backend.f1.backendf1.models;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Builder
@AllArgsConstructor
@Table(name = "team_scuderia")
@NamedQuery(name = "TeamFormulaOne.findAll", query = "SELECT tf FROM TeamFormulaOne tf")
@NoArgsConstructor
public class TeamFormulaOne extends AbstractPersistableCustom<Long> {

    @Column(name = "name", columnDefinition = "varchar(45)", unique = true, nullable = false)
    private String name;

    @Column(name = "alias", columnDefinition = "varchar(20)", nullable = false)
    private String alias;

    @Column(name = "year_register", columnDefinition = "varchar(5)", nullable = false)
    private String yearRegister;

    @Column(name = "team_chief", columnDefinition = "varchar(45)", nullable = false)
    private String teamChief;

    @Column(name = "technical_chief", columnDefinition = "varchar(45)", nullable = false)
    private String technicalChief;

    @Column(name = "date_created", nullable = false)
    private Date dateCreated;

    @Column(name = "date_updated")
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

    public TeamFormulaOne(Long id) {
        setId(id);
    }

}
