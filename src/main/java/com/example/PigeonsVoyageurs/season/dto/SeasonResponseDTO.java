package com.example.PigeonsVoyageurs.season.dto;

import java.time.LocalDateTime;

public record SeasonResponseDTO(String id , String name , LocalDateTime startDate , LocalDateTime endDate) {
}
