package com.cesardiaz.backend.f1.backendf1.models;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@NamedQuery(name = "ResultRace.findAll", query = "SELECT rr FROM ResultRace rr")
@Table(name = "result_race")
public class ResultRace extends AbstractPersistableCustom<Long>{

    @Column(name = "points", nullable = false)
    private Double points;

    @Column(name = "dnf", nullable = false)
    private Boolean dnf;
    @Column(name = "dns", nullable = false)
    private Boolean dns;
    @Column(name = "dnq", nullable = false)
    private Boolean dnq;
    
    @ManyToOne
    @JoinColumn(name = "record_fastlap_id")
    private RecordFastLap recordFastLap;
    
    @Column(name= "date_created")
    private LocalDate datecreated;
    
    @Column(name= "date_updated")
    private LocalDate dateUpdated;

    @ManyToOne
    @JoinColumn(name = "race_driver_id")
    private DriverFormulaOne driver;

    @ManyToOne
    @JoinColumn(name = "schedule_circuit_id")
    private ScheduleGranPrix scheduleGranPrix;

    public ResultRace(Double points, Boolean dnf, Boolean dns, Boolean dnq, RecordFastLap recordFastLap,
            LocalDate datecreated, LocalDate dateUpdated, DriverFormulaOne driver, ScheduleGranPrix scheduleGranPrix) {
        this.points = points;
        this.dnf = dnf;
        this.dns = dns;
        this.dnq = dnq;
        this.recordFastLap = recordFastLap;
        this.datecreated = datecreated;
        this.dateUpdated = dateUpdated;
        this.driver = driver;
        this.scheduleGranPrix = scheduleGranPrix;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Boolean getDnf() {
        return dnf;
    }

    public void setDnf(Boolean dnf) {
        this.dnf = dnf;
    }

    public Boolean getDns() {
        return dns;
    }

    public void setDns(Boolean dns) {
        this.dns = dns;
    }

    public Boolean getDnq() {
        return dnq;
    }

    public void setDnq(Boolean dnq) {
        this.dnq = dnq;
    }

    public RecordFastLap getRecordFastLap() {
        return recordFastLap;
    }

    public void setRecordFastLap(RecordFastLap recordFastLap) {
        this.recordFastLap = recordFastLap;
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

    public DriverFormulaOne getDriver() {
        return driver;
    }

    public void setDriver(DriverFormulaOne driver) {
        this.driver = driver;
    }

    public ScheduleGranPrix getScheduleGranPrix() {
        return scheduleGranPrix;
    }

    public void setScheduleGranPrix(ScheduleGranPrix scheduleGranPrix) {
        this.scheduleGranPrix = scheduleGranPrix;
    }

    
}
