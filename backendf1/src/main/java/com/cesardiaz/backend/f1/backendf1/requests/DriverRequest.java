package com.cesardiaz.backend.f1.backendf1.requests;


import com.cesardiaz.backend.f1.backendf1.dtos.UserAppDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DriverRequest {

    private String firstname;

    private String lastname;

    private String gamertag;

    private String numberDriver;

    private Long userId;
}
