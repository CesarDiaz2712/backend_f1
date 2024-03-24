package com.cesardiaz.backend.f1.backendf1.dtos;


public class DriverDTO {

    private Long id;
    private String name;
    private String lastname;
    private String gamertag;
    private String numberDriver;
    private String datecreated;
    private String dateUpdated;
    private UserAppDTO user;
    private TeamDTO team;

    public DriverDTO(Long id, String name, String lastname, String gamertag, String numberDriver, String datecreated,
            String dateUpdated, UserAppDTO user) {
        this.id=id;
        this.name = name;
        this.lastname = lastname;
        this.gamertag = gamertag;
        this.numberDriver = numberDriver;
        this.datecreated = datecreated;
        this.dateUpdated = dateUpdated;
        this.user = user;
    }

    
    public DriverDTO(Long id, String name, String lastname, String gamertag, String numberDriver, String datecreated,
            String dateUpdated, UserAppDTO user, TeamDTO team) {
        this.id=id;
        this.name = name;
        this.lastname = lastname;
        this.gamertag = gamertag;
        this.numberDriver = numberDriver;
        this.datecreated = datecreated;
        this.dateUpdated = dateUpdated;
        this.user = user;
        this.team = team;
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

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getGamertag() {
        return gamertag;
    }

    public void setGamertag(String gamertag) {
        this.gamertag = gamertag;
    }

    public String getNumberDriver() {
        return numberDriver;
    }

    public void setNumberDriver(String numberDriver) {
        this.numberDriver = numberDriver;
    }

    public String getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(String datecreated) {
        this.datecreated = datecreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public UserAppDTO getUser() {
        return user;
    }

    public void setUser(UserAppDTO user) {
        this.user = user;
    }

    public static DriverDTO instance(Long id, String name, String lastname, String gamertag, String numberDriver, String datecreated,
    String dateUpdated, UserAppDTO user){
        return new DriverDTO(id, name, lastname, gamertag, numberDriver, datecreated, dateUpdated, user);
    }
}
