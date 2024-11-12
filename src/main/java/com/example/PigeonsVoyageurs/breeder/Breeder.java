package com.example.PigeonsVoyageurs.breeder;

import com.example.PigeonsVoyageurs.coordinate.Coordinate;
import com.example.PigeonsVoyageurs.pigeon.Pigeon;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "breeders")
public class Breeder {
    @Id
    private String id;
    private String userName;
    private String password;
    private String email;
    private Coordinate coordinates;

    @DBRef
    private List<Pigeon> pigeons;

    public Breeder() {
    }

    public Breeder(String id, String userName, String password, String email, Coordinate coordinates) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.coordinates = coordinates;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Coordinate getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinate coordinates) {
        this.coordinates = coordinates;
    }

    // Getters and Setters
}