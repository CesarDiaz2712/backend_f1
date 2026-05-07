package com.cesardiaz.backend.f1.backendf1.core.advice;

import org.springframework.http.HttpStatus;

import com.cesardiaz.backend.f1.backendf1.core.constants.ErrorKeyEnum;

public class ResourceNotFoundException  extends CustomException{

	private static final HttpStatus httpStatus=HttpStatus.NOT_FOUND;
    
    public ResourceNotFoundException(ErrorKeyEnum errorKeyEnum) {
        super(errorKeyEnum, httpStatus);
    }
}
