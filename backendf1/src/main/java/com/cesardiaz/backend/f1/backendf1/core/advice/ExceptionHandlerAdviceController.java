package com.cesardiaz.backend.f1.backendf1.core.advice;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cesardiaz.backend.f1.backendf1.core.annotation.ExceptionLogger;
import com.cesardiaz.backend.f1.backendf1.core.constants.ErrorKeyEnum;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@ControllerAdvice
public class ExceptionHandlerAdviceController {

	@ExceptionLogger
	@ResponseBody
	@ExceptionHandler(CustomException.class)
	<T> ResponseEntity<ApiResponseError<T>> resourceNotFoundException(CustomException ex) {

		return buildResponse(ex.getKeyError(), ex.getHttpStatus(), null, ex.getParams());
	}

	@ExceptionLogger
	@ResponseBody
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "500", description = "Internal Server Error", 
				    content = { @Content(mediaType = "application/json", 
				      schema = @Schema(implementation = ExceptionAdviceSwagger.ApiResponseErrorInternalServerError.class))})	
	})
	ApiResponseError<?> nullpointerException(NullPointerException ex) {

		return ApiResponseError.builder().body(ex.getMessage())
				.error(ApiErrorEnum.NULLPOINTER.getDescripcion()).build();
	}

	@ExceptionLogger
	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "500", description = "Internal Server Error", 
				    content = { @Content(mediaType = "application/json", 
				      schema = @Schema(implementation = ExceptionAdviceSwagger.ApiResponseErrorInternalServerError.class))})	
	})
	ApiResponseError<?> exception(Exception ex) {

		return ApiResponseError.builder().body(ex.getMessage())
				.error(ApiErrorEnum.INTERNAL_SERVER_ERROR.getDescripcion())
				.code(ApiErrorEnum.INTERNAL_SERVER_ERROR.getClave()).build();
	}
	@ExceptionLogger
	@ResponseBody
	@ExceptionHandler(IllegalArgumentException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "400", description = "Bad Request", 
				    content = { @Content(mediaType = "application/json", 
				      schema = @Schema(implementation = ExceptionAdviceSwagger.ApiResponseErrorBadRequest.class))})	
	})
	ApiResponseError<?> ilegalArgumentException(IllegalArgumentException ex) {

		return ApiResponseError.builder().body(ex.getMessage())
				.error(ApiErrorEnum.INVALID_PARAMETERS.getDescripcion())
				.code(ApiErrorEnum.INVALID_PARAMETERS.getClave()).build();
	}

	<T>ResponseEntity<ApiResponseError<T>> buildResponse(ErrorKeyEnum errorKeyEnum, HttpStatus httpStatus, T body, List<Object> params){
		return new ResponseEntity<ApiResponseError<T>>(new ApiResponseError<>(null, errorKeyEnum.getMessage(), body), httpStatus);
	}

}
