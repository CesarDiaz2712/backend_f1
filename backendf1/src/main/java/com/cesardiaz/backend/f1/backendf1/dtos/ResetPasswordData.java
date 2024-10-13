package com.cesardiaz.backend.f1.backendf1.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ResetPasswordData implements Serializable{

    private String newPassword;
    private String oldPassword;
}
