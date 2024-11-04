package com.example.PigeonsVoyageurs.organiser;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class OrganiserDTO {

    @NotBlank(message = "Le nom d'utilisateur ne peut pas être vide")
    private String userName;

    @NotBlank(message = "Le mot de passe ne peut pas être vide")
    private String password;

    @NotBlank(message = "L'email ne peut pas être vide")
    @Email(message = "Email invalide")
    private String email;

    public OrganiserDTO(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
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
}
