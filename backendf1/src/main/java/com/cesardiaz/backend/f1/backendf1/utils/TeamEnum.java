package com.cesardiaz.backend.f1.backendf1.utils;

public enum TeamEnum {

    redbull(1,"Red Bull"),mercedes(2,"Mercedes"), mclaren(3,"Mclaren");

    private Integer id;
    private String code;

    private TeamEnum(Integer id, String code) {
        this.id = id;
        this.code = code;
    }

    
    public void setId(Integer id) {
        this.id = id;
    }


    public void setCode(String code) {
        this.code = code;
    }

    

    public Integer getId() {
        return id;
    }


    public String getCode() {
        return code;
    }


    @Override
    public String toString() {
        // TODO Auto-generated method stub
        return code.toLowerCase();
    }

    

    
}
