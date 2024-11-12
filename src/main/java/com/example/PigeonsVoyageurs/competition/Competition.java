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
    private double Distance;
    @DBRef
    private Season season;
    private List<Ranking> rankings;

    public Competition() {
    }

    public Competition(List<Ranking> rankings, Season season, double distance, LocalDateTime start, Coordinate coordinate, String raceName) {
        this.rankings = rankings;
        this.season = season;
        Distance = distance;
        this.start = start;
        this.coordinate = coordinate;
        this.raceName = raceName;
    }

    public Competition(Season season, double distance, LocalDateTime start, Coordinate coordinate, String raceName) {
        this.season = season;
        Distance = distance;
        this.start = start;
        this.coordinate = coordinate;
        this.raceName = raceName;
    }

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
        return Distance;
    }

    public void setDistance(double distance) {
        Distance = distance;
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