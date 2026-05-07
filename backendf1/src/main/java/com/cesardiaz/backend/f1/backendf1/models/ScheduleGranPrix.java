package com.cesardiaz.backend.f1.backendf1.models;

import java.time.LocalDate;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToOne;
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
@Builder
@AllArgsConstructor
@NamedQuery(name = "ScheduleGranPrix.findAll", query = "SELECT sc FROM ScheduleGranPrix sc")
@Table(name = "schedule_circuit")
public class ScheduleGranPrix extends AbstractPersistableCustom<Long>{

    @Column(name= "date_scheduled", nullable = false)
    private Date dateScheduled;

    @Column(name= "reversed")
    private Boolean reversed;  

    @Column(name= "date_created", nullable = false)
    private LocalDate datecreated;
    
    @Column(name= "date_updated", nullable = true)
    private LocalDate dateUpdated;

    @OneToOne
    @JoinColumn(name = "schedule_replaced_id")
    private ScheduleGranPrix circuitReplaced;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "circuit_id", nullable = false, foreignKey = @ForeignKey(name = "fk_schedule_circuit_circuit_id"))
    private Circuit circuit;

    public ScheduleGranPrix(Date dateScheduled, Boolean reversed, LocalDate datecreated, LocalDate dateUpdated,
            Circuit circuit) {
        this.dateScheduled = dateScheduled;
        this.reversed = reversed;
        this.datecreated = datecreated;
        this.dateUpdated = dateUpdated;
        this.circuit = circuit;
    }


    
}
