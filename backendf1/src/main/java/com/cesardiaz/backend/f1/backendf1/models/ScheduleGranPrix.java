package com.cesardiaz.backend.f1.backendf1.models;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@NamedQuery(name = "ScheduleGranPrix.findAll", query = "SELECT sc FROM ScheduleGranPrix sc")
@Table(name = "schedule_circuit")
public class ScheduleGranPrix extends AbstractPersistableCustom<Long>{

    @Column(name= "date_scheduled")
    private Date dateScheduled;

    @Column(name= "reversed")
    private Boolean reversed;  

    @Column(name= "date_created")
    private LocalDate datecreated;
    
    @Column(name= "date_updated")
    private LocalDate dateUpdated;

    @ManyToOne
    @JoinColumn(name = "circuit_id")
    private Circuit circuit;

    public ScheduleGranPrix(Date dateScheduled, Boolean reversed, LocalDate datecreated, LocalDate dateUpdated,
            Circuit circuit) {
        this.dateScheduled = dateScheduled;
        this.reversed = reversed;
        this.datecreated = datecreated;
        this.dateUpdated = dateUpdated;
        this.circuit = circuit;
    }

    public Date getDateScheduled() {
        return dateScheduled;
    }

    public void setDateScheduled(Date dateScheduled) {
        this.dateScheduled = dateScheduled;
    }

    public Boolean getReversed() {
        return reversed;
    }

    public void setReversed(Boolean reversed) {
        this.reversed = reversed;
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

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    
}
