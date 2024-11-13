package com.example.PigeonsVoyageurs.competition.dto;

import com.example.PigeonsVoyageurs.coordinate.Coordinate;
import com.example.PigeonsVoyageurs.ranking.Ranking;

import java.time.LocalDateTime;
import java.util.List;

public record CompetitionRequestDTO(
        String raceName,
        Coordinate coordinate,
        LocalDateTime start,
        double distance,
        String seasonId,
        List<Ranking> rankings
) {}
