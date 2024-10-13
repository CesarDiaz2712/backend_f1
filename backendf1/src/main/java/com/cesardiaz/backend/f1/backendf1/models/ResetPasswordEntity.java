package com.cesardiaz.backend.f1.backendf1.models;

import java.util.Date;

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
@NamedQuery(name = "ResetPasswordEntity.findAll", query = "SELECT p FROM ResetPasswordEntity p")
@Table(name = "reset_password")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ResetPasswordEntity extends AbstractPersistableCustom<Long>{

    @Column(name = "updated_by", columnDefinition = "varchar(45)", nullable = false)
    private String updatedBy;

    @Column(name = "created_on", nullable = false)
    private Date createdOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_app_id", nullable = false, foreignKey = @ForeignKey(name = "fk_reset_password_user_id"))
    private UserApp userApp;
    
    @Column(name = "old_password", nullable = false, length = 255)
    private String oldPassword;

    @Column(name = "new_password", nullable = false, length = 255)
    private String newPassword;
}
