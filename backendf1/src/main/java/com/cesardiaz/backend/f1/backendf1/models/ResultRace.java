package com.cesardiaz.backend.f1.backendf1.models;

import java.time.LocalDate;

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
@AllArgsConstructor
@Builder
@NamedQuery(name = "ResultRace.findAll", query = "SELECT rr FROM ResultRace rr")
@Table(name = "result_race")
public class ResultRace extends AbstractPersistableCustom<Long> {

    @Column(name = "points", nullable = false)
    private Double points;

    @Column(name = "dnf")
    private Boolean dnf;
    @Column(name = "dns")
    private Boolean dns;
    @Column(name = "dnq")
    private Boolean dnq;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "record_fastlap_id", nullable = false, foreignKey = @ForeignKey(name = "fk_result_race_record_fastlap_id"))
    private RecordFastLap recordFastLap;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = false, foreignKey = @ForeignKey(name = "fk_result_race_driver_id"))
    private DriverFormulaOne driver;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_circuit_id", nullable = false, foreignKey = @ForeignKey(name = "fk_result_race_schedule_circuit_id"))
    private ScheduleGranPrix scheduleGranPrix;

    @Column(name = "date_created", nullable = false)
    private LocalDate datecreated;

    @Column(name = "date_updated", nullable = true)
    private LocalDate dateUpdated;

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

}
