package com.cesardiaz.backend.f1.backendf1.components;

import java.time.LocalDate;

import com.cesardiaz.backend.f1.backendf1.requests.DriverRequest;
import org.springframework.stereotype.Component;

import com.cesardiaz.backend.f1.backendf1.dtos.DriverDTO;
import com.cesardiaz.backend.f1.backendf1.dtos.UserAppDTO;
import com.cesardiaz.backend.f1.backendf1.models.DriverFormulaOne;
import com.cesardiaz.backend.f1.backendf1.models.UserApp;

@Component
public class DriverConverterDTO {
    
    private final UserConverterDto userConverterDto;

    public DriverConverterDTO(UserConverterDto userConverterDto){
        this.userConverterDto = userConverterDto;
        
    }

    public DriverFormulaOne convertDTOToEntity(DriverDTO driverDto){

        if(driverDto == null){
            return null;
        }

        UserApp userApp = userConverterDto.convertDtoToEntity(driverDto.getUser());

        return DriverFormulaOne.instance(driverDto.getId(), driverDto.getName(), driverDto.getLastname(), driverDto.getGamertag(), driverDto.getNumberDriver(), 
        driverDto.getDatecreated()!=null? LocalDate.parse(driverDto.getDatecreated()):null, driverDto.getDateUpdated()!=null? LocalDate.parse(driverDto.getDateUpdated()):null, userApp);
    }

    public DriverFormulaOne convertDriverRequestToEntity(DriverRequest driverRequest, UserAppDTO userAppDTO){

        if(driverRequest == null){
            return null;
        }
        UserApp userApp = null;
        if (userAppDTO!=null)
            userApp = userConverterDto.convertDtoToEntity(userAppDTO);

        return DriverFormulaOne.builder().numberDriver(driverRequest.getNumberDriver()).firstname(driverRequest.getFirstname()).gamertag(driverRequest.getGamertag()).lastname(driverRequest.getLastname())
                .user(userApp).build();
    }

    public DriverDTO convertEntityToDTO(DriverFormulaOne driverEntity){

        if(driverEntity == null){
            return null;
        }

        UserAppDTO userAppDTO = userConverterDto.convertEntityToDto(driverEntity.getUser());

        return DriverDTO.instance(driverEntity.getId(), driverEntity.getFirstname(), driverEntity.getLastname(), driverEntity.getGamertag(), driverEntity.getNumberDriver(), 
        driverEntity.getDatecreated()!=null? driverEntity.getDatecreated().toString():null, driverEntity.getDateUpdated()!=null? driverEntity.getDateUpdated().toString():null, userAppDTO);
    }
}
