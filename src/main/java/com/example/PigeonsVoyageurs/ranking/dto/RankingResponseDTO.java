package com.example.PigeonsVoyageurs.ranking.dto;

public record RankingResponseDTO(
        String id,
        String competitionName,
        String pigeonRing,
        int position,
        double distance,
        java.time.LocalTime duration,
        double speed,
        double score
) {}
