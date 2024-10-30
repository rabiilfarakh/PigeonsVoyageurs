package com.example.PigeonsVoyageurs.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "rankings")
public class Ranking {
    @Id
    private Long id;
    private int rank;
    private Date arrive;
    private double distance;
    private double speed;
    private double score;
    // Référence vers le document Pigeon
    @DBRef
    private Pigeon pigeon;
    // Référence vers le document Competition
    @DBRef
    private Competition competition;

    public Ranking(Long id, int rank, Date arrive, double distance, double speed, double score, Pigeon pigeon, Competition competition) {
        this.id = id;
        this.rank = rank;
        this.arrive = arrive;
        this.distance = distance;
        this.speed = speed;
        this.score = score;
        this.pigeon = pigeon;
        this.competition = competition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public Date getArrive() {
        return arrive;
    }

    public void setArrive(Date arrive) {
        this.arrive = arrive;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Pigeon getPigeon() {
        return pigeon;
    }

    public void setPigeon(Pigeon pigeon) {
        this.pigeon = pigeon;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    // Getters and Setters
}