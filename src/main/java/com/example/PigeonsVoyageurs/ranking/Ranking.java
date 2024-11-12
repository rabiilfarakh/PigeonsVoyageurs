package com.example.PigeonsVoyageurs.ranking;

import com.example.PigeonsVoyageurs.competition.Competition;
import com.example.PigeonsVoyageurs.pigeon.Pigeon;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "rankings")
public class Ranking {
    @Id
    private String id;

    @DBRef
    private Competition competition;

    @DBRef
    private Pigeon pigeon;

    private int position;

}
