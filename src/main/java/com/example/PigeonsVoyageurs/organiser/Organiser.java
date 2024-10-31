package com.example.PigeonsVoyageurs.organiser;

import com.example.PigeonsVoyageurs.entity.Competition;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "organisers")
public class Organiser {
    @Id
    private Long id;
    private String userName;
    private String password;
    // Relation One-to-Many avec Competition
    @DBRef
    private List<Competition> competitions;

    public Organiser(Long id, String userName, String password) {
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

    // Getters and Setters
}
