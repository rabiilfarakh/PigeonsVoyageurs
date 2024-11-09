package com.example.PigeonsVoyageurs.season.dto;

import java.time.LocalDateTime;

public record SeasonRequestDTO(String name , LocalDateTime startDate , LocalDateTime endDate) {
}
