package com.example.PigeonsVoyageurs.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "breeders")
public class Breeder {
    @Id
    private Long id;
    private String dovecoteName;
    private String userName;
    private String password;
    private Coordinate coordinates;

    public Breeder(Long id, String dovecoteName, String userName, String password, Coordinate coordinates) {
        this.id = id;
        this.dovecoteName = dovecoteName;
        this.userName = userName;
        this.password = password;
        this.coordinates = coordinates;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDovecoteName() {
        return dovecoteName;
    }

    public void setDovecoteName(String dovecoteName) {
        this.dovecoteName = dovecoteName;
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

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    // Getters and Setters
}