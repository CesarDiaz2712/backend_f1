package com.cesardiaz.backend.f1.backendf1.core.advice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.cesardiaz.backend.f1.backendf1.core.annotation.ExceptionLogger;

import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;

@ControllerAdvice
public class ExceptionHandlerAdviceController {
	@ExceptionLogger
	@ResponseBody
	@ExceptionHandler(ResourceNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "404", description = "Not Found", 
				    content = { @Content(mediaType = "application/json", 
				      schema = @Schema(implementation = ExceptionAdviceSwagger.ApiResponseErrorNotFound.class))})	
	})
	ApiResponseError<?> resourceNotFoundException(ResourceNotFoundException ex) {

		return ApiResponseError.builder().body(ex.getMessage())
				.error(ApiErrorEnum.RESOURCE_NOT_FOUND.getDescripcion())
				.code(ApiErrorEnum.RESOURCE_NOT_FOUND.getClave()).build();
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

	@ExceptionLogger
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseBody
	ErrorResult handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
		ErrorResult errorResult = new ErrorResult();
		for (FieldError fieldError : e.getBindingResult().getFieldErrors()) {
			errorResult.getFieldErrors()
					.add(new FieldValidationError(fieldError.getField(), fieldError.getDefaultMessage()));
		}
		return errorResult;
	}

	@Getter
	@NoArgsConstructor
	static class ErrorResult {
		private final List<FieldValidationError> fieldErrors = new ArrayList<>();

		ErrorResult(String field, String message) {
			this.fieldErrors.add(new FieldValidationError(field, message));
		}
	}

	@Getter
	@AllArgsConstructor
	static class FieldValidationError {
		private String field;
		private String message;
	}

}
