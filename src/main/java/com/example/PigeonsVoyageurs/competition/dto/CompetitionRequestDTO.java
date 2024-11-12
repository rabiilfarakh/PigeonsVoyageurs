package com.example.PigeonsVoyageurs.competition.dto;

import com.example.PigeonsVoyageurs.coordinate.Coordinate;
import com.example.PigeonsVoyageurs.season.Season;
import java.time.LocalDateTime;

public record CompetitionRequestDTO(String raceName, Coordinate coordinate, LocalDateTime start, double Distance, Season season) {
}
