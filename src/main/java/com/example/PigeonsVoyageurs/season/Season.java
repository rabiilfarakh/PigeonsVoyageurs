package com.example.PigeonsVoyageurs.season;


import com.example.PigeonsVoyageurs.competition.Competition;
import lombok.*;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Document(collection = "seasons")
public class Season {
    @Id
    private String id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @DBRef
    private List<Competition> competitions = new ArrayList<>();

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public void setCompetitions(List<Competition> competitions) {
        this.competitions = competitions;
    }

    public Season() {
    }

    public Season(String name, LocalDateTime startDate, LocalDateTime endDate, List<Competition> competitions) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.competitions = competitions;
    }

    public void setId(String id) {
        this.id = id;
    }
}