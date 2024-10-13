package com.cesardiaz.backend.f1.backendf1.models;

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
@AllArgsConstructor
@NoArgsConstructor
@Builder
@NamedQuery(name = "Code.findAll", query = "SELECT c FROM Code c")
@Table(name = "code")
public class Code extends AbstractPersistableCustom<Long>{

    @Column(name = "code", nullable = false)
    private String code;

    
}
