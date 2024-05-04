package com.cesardiaz.backend.f1.backendf1.projections;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public interface DriverDataView {
    
	@JsonInclude(value = Include.NON_NULL)
    Long getDriverId();

	@JsonInclude(value = Include.NON_NULL)
    Long getIdContract();

	@JsonInclude(value = Include.NON_NULL)
    String getName();

	@JsonInclude(value = Include.NON_NULL)
    String getLastname();
	
    @JsonInclude(value = Include.NON_NULL)
    String getGamertag();

    @JsonInclude(value = Include.NON_NULL)
    String getNumberDriver();

    @JsonInclude(value = Include.NON_NULL)
    String getDateCreated();

    @JsonInclude(value = Include.NON_NULL)
    Long getUserId();

    @JsonInclude(value = Include.NON_NULL)
    String getInitialDateContract();

    @JsonInclude(value = Include.NON_NULL)
    String getFinalDateContract();

    @JsonInclude(value = Include.NON_NULL)
    Boolean getStatus();
}
