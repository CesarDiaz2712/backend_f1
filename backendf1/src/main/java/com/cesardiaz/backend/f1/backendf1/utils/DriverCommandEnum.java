package com.cesardiaz.backend.f1.backendf1.utils;

public enum DriverCommandEnum {

    actual_drivers("actual_drivers"), all_drivers("all_drivers");

    private String code;

    private DriverCommandEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    
}
