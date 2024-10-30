package com.example.PigeonsVoyageurs.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "competitions")
public class Competition {
    @Id
    private Long id;
    private String raceName;
    private Coordinate releasePoint;
    private Date start;
    private double previewedDistance;
    // Relation Many-to-Many avec Pigeon
    @DBRef
    private List<Pigeon> participants;
    // Liste des classements pour chaque pigeon dans la compétition
    private List<Ranking> rankings;

    public Competition(Long id, String raceName, Coordinate releasePoint, Date start, double previewedDistance, List<Pigeon> participants, List<Ranking> rankings) {
        this.id = id;
        this.raceName = raceName;
        this.releasePoint = releasePoint;
        this.start = start;
        this.previewedDistance = previewedDistance;
        this.participants = participants;
        this.rankings = rankings;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public Coordinate getReleasePoint() {
        return releasePoint;
    }

    public void setReleasePoint(Coordinate releasePoint) {
        this.releasePoint = releasePoint;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public double getPreviewedDistance() {
        return previewedDistance;
    }

    public void setPreviewedDistance(double previewedDistance) {
        this.previewedDistance = previewedDistance;
    }

    public List<Pigeon> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Pigeon> participants) {
        this.participants = participants;
    }

    public List<Ranking> getRankings() {
        return rankings;
    }

    public void setRankings(List<Ranking> rankings) {
        this.rankings = rankings;
    }


}
