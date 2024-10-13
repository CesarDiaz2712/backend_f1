package com.cesardiaz.backend.f1.backendf1.core.advice;

import org.springframework.http.HttpStatus;

import com.cesardiaz.backend.f1.backendf1.core.constants.ErrorKeyEnum;

public class BadRequestCustomException extends CustomException{

	private static final HttpStatus httpStatus=HttpStatus.BAD_REQUEST;

    public BadRequestCustomException(ErrorKeyEnum errorKeyEnum, Object[] params) {
        super(errorKeyEnum, httpStatus, params);
    }

}
