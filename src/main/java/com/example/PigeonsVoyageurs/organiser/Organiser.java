package com.example.PigeonsVoyageurs.organiser;

import com.example.PigeonsVoyageurs.competition.Competition;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@Document(collection = "organisers")
public class Organiser {
    @Id
    private Long id;
    private String userName;
    private String password;
    private String token;
    // Relation One-to-Many avec Competition
    @DBRef
    private List<Competition> competitions;

}
