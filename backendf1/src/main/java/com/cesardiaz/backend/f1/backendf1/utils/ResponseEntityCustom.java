package com.cesardiaz.backend.f1.backendf1.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class ResponseEntityCustom {

    public static <T> ResponseEntity<?> getResponseEntityClass(T customClass, HttpStatus httpStatus){
        
        return new ResponseEntity<T>(customClass, httpStatus);
    }

    public static ResponseEntity<String> getResponseEntity(String message, HttpStatus httpStatus){

        return new ResponseEntity<String>("Message: " + message, httpStatus);
    }
}
