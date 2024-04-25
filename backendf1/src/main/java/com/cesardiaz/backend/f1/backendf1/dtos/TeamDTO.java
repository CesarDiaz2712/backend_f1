package com.cesardiaz.backend.f1.backendf1.dtos;

public class TeamDTO {

    private Long id;
    private String name;
    private String yearRegister;
    private String alias;
    private String teamChief;
    private String technicalChief;
    private String createdDate;
    private String updatedDate;
    
    public TeamDTO(Long id, String name, String alias, String teamChief, String technicalChief,
            String createdDate, String yearRegister, String updatedDate) {
        this.id = id;
        this.name = name;
        this.alias = alias;
        this.teamChief = teamChief;
        this.technicalChief = technicalChief;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.yearRegister = yearRegister;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getTeamChief() {
        return teamChief;
    }

    public void setTeamChief(String teamChief) {
        this.teamChief = teamChief;
    }

    public String getTechnicalChief() {
        return technicalChief;
    }

    public void setTechnicalChief(String technicalChief) {
        this.technicalChief = technicalChief;
    }

    public String getYearRegister() {
        return yearRegister;
    }

    public void setYearRegister(String yearRegister) {
        this.yearRegister = yearRegister;
    }

    public String getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(String createdDate) {
        this.createdDate = createdDate;
    }

    public String getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        this.updatedDate = updatedDate;
    }
    
}
