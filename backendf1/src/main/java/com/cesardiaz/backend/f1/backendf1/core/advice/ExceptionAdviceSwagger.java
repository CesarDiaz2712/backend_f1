package com.cesardiaz.backend.f1.backendf1.core.advice;

import io.swagger.v3.oas.annotations.media.Schema;

public class ExceptionAdviceSwagger {

	public final static class ApiResponseErrorNotFound {
		@Schema(example = "404")
		public String code;
		@Schema(example = "Could not find resource.")
		public String error;
		@Schema(example = "Message Exception")
		public String body;
	}
	
	public final static class ApiResponseErrorInternalServerError {
		@Schema(example = "500")
		public String code;
		@Schema(example = "An unknown Api exception was thrown")
		public String error;
		@Schema(example = "Message Exception")
		public String body;
	}
	
	public final static class ApiResponseErrorBadRequest {
		@Schema(example = "400")
		public String code;
		@Schema(example = "A required parameter for this API operation is invalid or has not been provided")
		public String error;
		@Schema(example = "Message Exception")
		public String body;
	}

}
