package com.cesardiaz.backend.f1.backendf1.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.cesardiaz.backend.f1.backendf1.components.DriverConverterDTO;
import com.cesardiaz.backend.f1.backendf1.dtos.DriverDTO;
import com.cesardiaz.backend.f1.backendf1.models.DriverFormulaOne;
import com.cesardiaz.backend.f1.backendf1.models.UserApp;
import com.cesardiaz.backend.f1.backendf1.repositories.DriverFormulaRepository;
import com.cesardiaz.backend.f1.backendf1.repositories.UserRepository;
import com.cesardiaz.backend.f1.backendf1.services.DriverServiceImp;
import com.cesardiaz.backend.f1.backendf1.utils.validation.DriverValidationRequest;

@ExtendWith(SpringExtension.class)
public class DriverServiceTest {

    @Mock
    DriverFormulaRepository driverFormulaRepository;

    @Mock
    DriverValidationRequest driverValidationRequest;
    @Mock
     DriverFormulaRepository driverRepository; 
     @Mock
     UserRepository userRepository; 
     @Mock
     DriverConverterDTO driverConverterDTO;
    // @MockBean
    @InjectMocks
    DriverServiceImp driverService;

    @BeforeEach
    public void init(){
		// driverService = new DriverServiceImp( driverValidationRequest,  driverRepository,  userRepository,  driverConverterDTO);

    }

    @Test
    public void getDriverByIdSuccess() {
        Long id = 9l;
        Optional<DriverFormulaOne> driverToFindOptional =  Optional.of(DriverFormulaOne.createDriver(id, "Cesar", "alejo", "stric", "27",
                LocalDate.now(), null, new UserApp(1l)));

                when(driverFormulaRepository.findById(anyLong())).thenReturn(driverToFindOptional);


                DriverDTO dto =   driverService.getDriverDTO(id);
                DriverFormulaOne entity = driverToFindOptional.get();
                assertThat(dto.getId()).isSameAs(entity.getId());
                assertThat(dto.getName()).isSameAs(entity.getFirstname());
                assertThat(dto.getLastname()).isSameAs(entity.getLastname());
                assertThat(dto.getGamertag()).isSameAs(entity.getGamertag());
                assertThat(dto.getNumberDriver()).isSameAs(entity.getNumberDriver());
                // assertThat(dto.getTeam()).isSameAs(entity.getTeam());
                assertThat(dto.getUser()).isSameAs(entity.getUser());
                assertThat(dto.getDatecreated()).isSameAs(entity.getDatecreated());
    }
}
