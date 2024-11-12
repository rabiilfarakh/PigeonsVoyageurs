package com.example.PigeonsVoyageurs.competition;

import com.example.PigeonsVoyageurs.competition.dto.CompetitionRequestDTO;
import com.example.PigeonsVoyageurs.competition.dto.CompetitionResponseDTO;

public interface CompetitionMapper {
    CompetitionResponseDTO toResponseDTO(Competition competition);
    Competition toEntity(CompetitionRequestDTO competitionDTO);
}
