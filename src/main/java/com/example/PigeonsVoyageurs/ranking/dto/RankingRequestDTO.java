package com.example.PigeonsVoyageurs.ranking.dto;

import java.time.LocalTime;

public record RankingRequestDTO(
        String competitionId,
        String pigeonId,
        int position,
        double distance,
        LocalTime duration,
        double speed,
        double score
) {}
