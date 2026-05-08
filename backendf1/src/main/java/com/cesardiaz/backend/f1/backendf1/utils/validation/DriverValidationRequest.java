package com.cesardiaz.backend.f1.backendf1.utils.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.cesardiaz.backend.f1.backendf1.core.advice.UnsopportedParamsException;
import com.cesardiaz.backend.f1.backendf1.requests.DriverRequest;
import org.springframework.stereotype.Component;

@Component
public class DriverValidationRequest extends ValidationParamsAbstract{

    /**
     * Validates that all required fields for creating a new driver are present
     * and not blank.
     * <p>
     * Required fields: {@code numberDriver}, {@code firstname},
     * {@code lastname}, and {@code gamertag}.
     * </p>
     *
     * @param driverRequest the request object containing the new driver's data
     * @throws UnsopportedParamsException if one or more required fields are
     *                                    null, empty, or blank
     */
    public void validateParamsToCreate(DriverRequest driverRequest) {

        List<String> params = new ArrayList<>();

        validateBlank(driverRequest.getFirstname(),    "firstname",    params);
        validateBlank(driverRequest.getLastname(),     "lastname",     params);
        validateBlank(driverRequest.getGamertag(),     "gamertag",     params);
        validateNull(driverRequest.getNumberDriver(),  "numberDriver", params);
        validateNull(driverRequest.getUserId(),  "userId", params);

        if (!params.isEmpty())
            throw new UnsopportedParamsException(params);
    }
}
