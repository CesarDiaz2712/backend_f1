package com.cesardiaz.backend.f1.backendf1.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DetailsUserData {

    private String userId;
    private String username;
    private String firstname;
    private String lastname;
}
