package com.example.PigeonsVoyageurs.season;


import com.example.PigeonsVoyageurs.competition.Competition;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Document(collection = "seasons")
public class Season {
    private String id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @DBRef
    private List<Competition> competitions;

    public Season() {
    }

    public Season(String name, LocalDateTime startDate, LocalDateTime endDate, List<Competition> competitions) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.competitions = competitions;
    }
}