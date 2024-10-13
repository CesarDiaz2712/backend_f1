package com.cesardiaz.backend.f1.backendf1.models;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@Builder
@NoArgsConstructor
@NamedQuery(name = "RecordFastLap.findAll", query = "SELECT rf FROM RecordFastLap rf")
@Table(name = "record_fastlap")
public class RecordFastLap extends AbstractPersistableCustom<Long>{
    
    @Column(name= "record", columnDefinition="varchar(10)")
    private String recordFastLap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "circuit_id", nullable = false, foreignKey = @ForeignKey(name = "fk_record_fastlap_circuit_id"))
    private Circuit circuit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cv_seasson_id", nullable = false, foreignKey = @ForeignKey(name = "fk_record_fastlap_cv_seasson_id"))
    private CodeValue codeValueSesson;
    
    @Column(name= "active")
    private Boolean active;

    @Column(name= "date_created")
    @Temporal(TemporalType.DATE)
    private Date datecreated;
    
    @Column(name= "date_updated")
    @Temporal(TemporalType.DATE)
    private Date dateUpdated;

    
    
}
