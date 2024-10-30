package com.example.PigeonsVoyageurs.entity;

import org.springframework.data.annotation.Id;
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
    private Organiser organiser;  // Reference to Organiser
    private List<Pigeon> pigeons; // List of Pigeons participating

    public Competition(Long id, String raceName, Coordinate releasePoint, Date start, double previewedDistance, Organiser organiser, List<Pigeon> pigeons) {
        this.id = id;
        this.raceName = raceName;
        this.releasePoint = releasePoint;
        this.start = start;
        this.previewedDistance = previewedDistance;
        this.organiser = organiser;
        this.pigeons = pigeons;
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

    public Organiser getOrganiser() {
        return organiser;
    }

    public void setOrganiser(Organiser organiser) {
        this.organiser = organiser;
    }

    public List<Pigeon> getPigeons() {
        return pigeons;
    }

    public void setPigeons(List<Pigeon> pigeons) {
        this.pigeons = pigeons;
    }

    // Getters and Setters
}
