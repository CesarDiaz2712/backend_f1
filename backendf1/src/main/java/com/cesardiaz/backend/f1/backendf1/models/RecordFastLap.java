package com.cesardiaz.backend.f1.backendf1.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@NamedQuery(name = "RecordFastLap.findAll", query = "SELECT rf FROM RecordFastLap rf")
@Table(name = "record_fastlap")
public class RecordFastLap extends AbstractPersistableCustom<Long>{
    
    @Column(name= "number_driver", columnDefinition="varchar(2)")
    private String recordFastLap;
    
    @ManyToOne
    @JoinColumn(name = "result_race_id", nullable = false)
    private ResultRace resultRace;

    @ManyToOne
    @JoinColumn(name = "circuit_id", nullable = false)
    private Circuit circuit;

    @ManyToOne
    @JoinColumn(name = "code_value_seasson_id", nullable = false)
    private CodeValue codeValueSesson;
    
    @Column(name= "status")
    private Integer status;

    @Column(name= "date_created")
    @Temporal(TemporalType.DATE)
    private Date datecreated;
    
    @Column(name= "date_updated")
    @Temporal(TemporalType.DATE)
    private Date dateUpdated;

    public RecordFastLap(String recordFastLap, com.cesardiaz.backend.f1.backendf1.models.ResultRace resultRace,
            Circuit circuit, CodeValue codeValueSesson, Integer status, Date datecreated, Date dateUpdated) {
        this.recordFastLap = recordFastLap;
        this.resultRace = resultRace;
        this.circuit = circuit;
        this.codeValueSesson = codeValueSesson;
        this.status = status;
        this.datecreated = datecreated;
        this.dateUpdated = dateUpdated;
    }

    public String getRecordFastLap() {
        return recordFastLap;
    }

    public void setRecordFastLap(String recordFastLap) {
        this.recordFastLap = recordFastLap;
    }

    public ResultRace getResultRace() {
        return resultRace;
    }

    public void setResultRace(ResultRace resultRace) {
        this.resultRace = resultRace;
    }

    public Circuit getCircuit() {
        return circuit;
    }

    public void setCircuit(Circuit circuit) {
        this.circuit = circuit;
    }

    public CodeValue getCodeValueSesson() {
        return codeValueSesson;
    }

    public void setCodeValueSesson(CodeValue codeValueSesson) {
        this.codeValueSesson = codeValueSesson;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
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
