package com.cesardiaz.backend.f1.backendf1.core.advice;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

import com.cesardiaz.backend.f1.backendf1.core.constants.ErrorKeyEnum;

import lombok.Getter;

@Getter
public abstract class CustomException extends RuntimeException {

    private static final long serialVersionUID = 4482764241994136778L;

    private final transient List<Object> params;
    private final HttpStatus httpStatus;
    private final ErrorKeyEnum keyError;

    protected CustomException(ErrorKeyEnum errorKeyEnum, HttpStatus httpStatus) {
        super(errorKeyEnum.getMessage());
        this.httpStatus = httpStatus;
        this.keyError = errorKeyEnum;
        this.params=null;
    }
    
    protected CustomException(ErrorKeyEnum errorKeyEnum, HttpStatus httpStatus, Object... params) {
        super(errorKeyEnum.getMessage());
        this.httpStatus = httpStatus;
        this.keyError = errorKeyEnum;

        if(params == null){
            this.params = null;
        }else{
            this.params = Arrays.asList(params);
        }
    }
}
