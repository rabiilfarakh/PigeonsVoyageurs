package com.example.PigeonsVoyageurs.organiser;

import com.example.PigeonsVoyageurs.competition.Competition;

import java.util.List;

public class OrganiserDTO {
    private Long id;
    private String userName;
    private String password;
    private String token;
    // Relation One-to-Many avec Competition
    private List<Competition> competitions;

    public OrganiserDTO(Long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }
}
