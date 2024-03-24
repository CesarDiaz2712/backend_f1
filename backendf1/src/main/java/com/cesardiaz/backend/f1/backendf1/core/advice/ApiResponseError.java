package com.cesardiaz.backend.f1.backendf1.core.advice;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Value;


@JsonInclude(Include.NON_NULL)
@Value
@Builder
public class ApiResponseError<T> {

	private String code;
	private String error;
	private T body;


}
