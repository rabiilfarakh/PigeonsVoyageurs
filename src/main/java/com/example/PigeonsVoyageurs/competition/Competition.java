package com.example.PigeonsVoyageurs.competition;

import com.example.PigeonsVoyageurs.coordinate.Coordinate;
import com.example.PigeonsVoyageurs.pigeon.Pigeon;
import com.example.PigeonsVoyageurs.ranking.Ranking;
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

    @DBRef
    private List<Pigeon> pigeons;

    private List<Ranking> rankings;

    public Competition(){}

    public Competition(Long id, String raceName, Coordinate releasePoint, Date start, double previewedDistance, List<Pigeon> pigeons, List<Ranking> rankings) {
        this.id = id;
        this.raceName = raceName;
        this.releasePoint = releasePoint;
        this.start = start;
        this.previewedDistance = previewedDistance;
        this.pigeons = pigeons;
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
        return pigeons;
    }

    public void setParticipants(List<Pigeon> pigeons) {
        this.pigeons = pigeons;
    }

    public List<Ranking> getRankings() {
        return rankings;
    }

    public void setRankings(List<Ranking> rankings) {
        this.rankings = rankings;
    }
}