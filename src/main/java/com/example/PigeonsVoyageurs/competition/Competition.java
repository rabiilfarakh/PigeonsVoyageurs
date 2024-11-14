package com.example.PigeonsVoyageurs.competition;

import com.example.PigeonsVoyageurs.coordinate.Coordinate;
import com.example.PigeonsVoyageurs.ranking.Ranking;
import com.example.PigeonsVoyageurs.season.Season;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;


@Document(collection = "competitions")
public class Competition {
    @Id
    private String id;
    private String raceName;
    private Coordinate coordinate;
    private LocalDateTime start;
    private double distance;

    @DBRef
    private Season season;

    private List<Ranking> rankings;



    public String getId() {
        return id;
    }

    public String getRaceName() {
        return raceName;
    }

    public void setRaceName(String raceName) {
        this.raceName = raceName;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public LocalDateTime getStart() {
        return start;
    }

    public void setStart(LocalDateTime start) {
        this.start = start;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        distance = distance;
    }

    public Season getSeason() {
        return season;
    }

    public void setSeason(Season season) {
        this.season = season;
    }

    public List<Ranking> getRankings() {
        return rankings;
    }

    public void setRankings(List<Ranking> rankings) {
        this.rankings = rankings;
    }
}