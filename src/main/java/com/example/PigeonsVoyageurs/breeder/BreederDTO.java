package com.example.PigeonsVoyageurs.breeder;

import com.example.PigeonsVoyageurs.coordinate.Coordinate;
import com.example.PigeonsVoyageurs.pigeon.Pigeon;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.validation.constraints.NotEmpty;
import java.util.List;

public class BreederDTO {
    private Long id;
    @NotEmpty
    private String dovecoteName;
    @NotEmpty
    private String userName;
    @NotEmpty
    private String password;
    @NotEmpty
    private Coordinate coordinates;
    private String token;
    // Relation One-to-Many avec Pigeon
    private List<Pigeon> pigeons;

    public BreederDTO(String dovecoteName, String userName, String password, Coordinate coordinates) {
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Pigeon> getPigeons() {
        return pigeons;
    }

    public void setPigeons(List<Pigeon> pigeons) {
        this.pigeons = pigeons;
    }

    @Override
    public String toString() {
        return "{" +
                "dovecoteName='" + dovecoteName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", coordinates=" + coordinates +
                '}';
    }
}
