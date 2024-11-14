package com.example.PigeonsVoyageurs.pigeon.dto;

import com.example.PigeonsVoyageurs.enumeration.Sexe;
import com.example.PigeonsVoyageurs.ranking.Ranking;

import java.util.List;

public record PigeonResponseDTO(String pigeonRing, Sexe sexe, String color, int age, List<Ranking> rankings) {}
