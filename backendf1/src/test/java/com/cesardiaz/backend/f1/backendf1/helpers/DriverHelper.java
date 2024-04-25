package com.cesardiaz.backend.f1.backendf1.helpers;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import com.cesardiaz.backend.f1.backendf1.models.DriverFormulaOne;
import com.cesardiaz.backend.f1.backendf1.models.UserApp;

@Component
public class DriverHelper {

    private final UserHelper userHelper = new UserHelper();

    public DriverFormulaOne createDriverFormulaDefaultWithUser(){
        UserApp user = userHelper.createUserDefault();
        return new DriverFormulaOne("Cesar", "Alejo", "Stricline8931", "27", LocalDate.now(), null, user);
    }
    public DriverFormulaOne createDriverFormulaCustom(String name, String lastname, String gamertag, String numberDriver, UserApp user){
        // UserApp user = userHelper.createUserDefault();
        return new DriverFormulaOne(name, lastname, gamertag, numberDriver, LocalDate.now(), null, user);
    }
}
