package com.example.PigeonsVoyageurs.competition;

import com.example.PigeonsVoyageurs.entities.Coordinate;
import com.example.PigeonsVoyageurs.entities.Pigeon;
import com.example.PigeonsVoyageurs.entities.Ranking;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Data
@Document(collection = "competitions")
public class Competition {
    @Id
    private Long id;
    private String raceName;
    private Coordinate releasePoint;
    private Date start;
    private double previewedDistance;

    @DBRef
    private List<Pigeon> participants;

    private List<Ranking> rankings;
}