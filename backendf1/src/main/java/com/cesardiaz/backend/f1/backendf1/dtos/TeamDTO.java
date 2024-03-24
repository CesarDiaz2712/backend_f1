package com.cesardiaz.backend.f1.backendf1.dtos;

public class TeamDTO {

    private Long id;
    private String name;
    private String teamChief;

    

    public TeamDTO(Long id, String name, String teamChief) {
        this.id = id;
        this.name = name;
        this.teamChief = teamChief;
    }

    

    public TeamDTO(Long id) {
        this.id = id;
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
    public String getTeamChief() {
        return teamChief;
    }
    public void setTeamChief(String teamChief) {
        this.teamChief = teamChief;
    }

    
    
}
