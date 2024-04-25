package com.cesardiaz.backend.f1.backendf1.utils;

public enum TeamCommandEnum {

    actual_teams("actual_teams"), all_teams("all_teams");

    private String code;

    private TeamCommandEnum(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
