package com.example.PigeonsVoyageurs.pigeon;

import com.example.PigeonsVoyageurs.enumeration.Sexe;
import com.example.PigeonsVoyageurs.ranking.Ranking;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "pigeons")
public class Pigeon {
    @Id
    private String pigeonRing;
    private Sexe sexe;
    private String color;
    private int age;
    private List<Ranking> rankings;

    public Pigeon(){}

    public String getPigeonRing() {
        return pigeonRing;
    }

    public void setPigeonRing(String pigeonRing) {
        this.pigeonRing = pigeonRing;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<Ranking> getRankings() {
        return rankings;
    }

    public void setRankings(List<Ranking> rankings) {
        this.rankings = rankings;
    }
}